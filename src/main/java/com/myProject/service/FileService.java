
package com.myProject.service;

import com.myProject.dto.FileDto;
import com.myProject.entity.Message;

import java.util.*;

public interface FileService {

    void saveFile(Message message,  Set<FileDto> files);

    List<FileDto> getFilesByMessage(Long messageId);

    void deleteFilesByMessage(List<Long> messageId);



}
