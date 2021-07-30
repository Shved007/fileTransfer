package com.myProject.web.advice.rest;

import java.util.*;

import com.myProject.dto.FileUploadResponseDto;
import com.myProject.dto.UserDto;
import com.myProject.entity.FileDocument;
import com.myProject.entity.User;
import com.myProject.repository.FileRepository;
import com.myProject.service.impl.FileStoreServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class UploadDownloadFileResource {

    private final FileRepository fileRepository;
    private final FileStoreServiceImpl fileStoreService;
    private final ModelMapper modelMapper;

    public UploadDownloadFileResource(FileRepository fileRepository, FileStoreServiceImpl fileStoreService, ModelMapper modelMapper) {
        this.fileRepository = fileRepository;
        this.fileStoreService = fileStoreService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/upload")
    public FileUploadResponseDto singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = fileStoreService.storeFile(file);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();

        String contentType = file.getContentType();

        FileUploadResponseDto responseDto = new FileUploadResponseDto(fileName, contentType, url);

        return responseDto;
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStoreService.downloadFile(fileName);

        String mimeType = request.getServletContext().getMimeType(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + fileName)
                .body(resource);

    }

    @PostMapping("/multiple/upload")
    List<FileUploadResponseDto> multipleUpload(@RequestParam("files") MultipartFile[] files) {

        if (files.length > 5) {
            throw new RuntimeException("too many files");
        }

        List<FileUploadResponseDto> uploadResponseDtos = new ArrayList<>();

        Arrays.stream(files)
                .forEach(file -> {
                    String fileName = fileStoreService.storeFile(file);

                    String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/download/")
                            .path(fileName)
                            .toUriString();

                    String contentType = file.getContentType();

                    FileUploadResponseDto responseDto = new FileUploadResponseDto(fileName, contentType, url);

                    uploadResponseDtos.add(responseDto);
                });
        return uploadResponseDtos;
    }

    @GetMapping("zipDownload")
    void zipDownload(@RequestParam("fileName") String[] files, HttpServletResponse response) throws IOException {

        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {

            Arrays.asList(files)
                    .stream()
                    .forEach(file -> {
                        Resource resource = fileStoreService.downloadFile(file);

                        ZipEntry zipEntry = new ZipEntry(resource.getFilename());

                        try {
                            zipEntry.setSize(resource.contentLength());
                            zos.putNextEntry(zipEntry);

                            StreamUtils.copy(resource.getInputStream(), zos);

                            zos.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            zos.finish();

        }
    }
}
