package com.myProject.service.impl;

import com.myProject.dto.FileDto;
import com.myProject.entity.FileDocument;
import com.myProject.entity.Message;
import com.myProject.repository.FileRepository;
import com.myProject.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void saveFile(Message message,  Set<FileDto> files) {


//        if (files == null || files.isEmpty()){
//            return;
//        }

        if (CollectionUtils.isEmpty(files)){
            return;
        }
        files.forEach(f -> {
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(f.getFileName());
            fileDocument.setMessage(message);
            fileRepository.save(fileDocument);
        });
    }

    @Override
    public List<FileDto> getFilesByMessage(Long messageId) {
        return fileRepository.findByMessageId(messageId).stream().map(f -> {
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(f.getFileName())
                    .toUriString();
            FileDto fileDto = new FileDto();
            fileDto.setFileName(url);

            return fileDto;

        }).collect(Collectors.toList());
    }

    @Override
    public void deleteFilesByMessage(List<Long> messageId) {
        List<FileDocument> byMessageId = fileRepository.findByMessageIdIn(messageId);
        fileRepository.deleteAll(byMessageId);
    }
}
