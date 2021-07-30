package com.myProject.repository;

import com.myProject.entity.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileDocument,Long> {

    FileDocument findByFileName(String fileName);

    List<FileDocument> findByMessageId(Long id);

    List<FileDocument> findByMessageIdIn(List<Long> ids);
}
