package com.myProject.web.advice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileResource {
    private final String UPLOAD_PATH = "upload";

    @PostMapping(value = "/files")
    public ResponseEntity<?> uploadFiles(@RequestParam("image") MultipartFile multipartFile) {
        //шлях до проекту
        String projectPath = System.getProperty("user.dir");
        //шлях до папки для збереження файлу
        String storagePath = projectPath + File.separator + "src" + File.separator + UPLOAD_PATH + File.separator;

        try {

            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(storagePath + multipartFile.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

}
