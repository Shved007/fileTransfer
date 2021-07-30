package com.myProject.service;

import com.myProject.dto.CodeDto;
import com.myProject.dto.MessageDto;

import java.util.List;
public interface CodeService {

    CodeDto create(MessageDto messageDto);

    void validateCode(String code);

    void deleteByCode(String code);

    List<CodeDto> findAll();
}
