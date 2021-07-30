package com.myProject.service.impl;

import com.myProject.config.AppProperties;
import com.myProject.dto.CodeDto;
import com.myProject.dto.MessageDto;
import com.myProject.entity.Code;
import com.myProject.entity.Message;
import com.myProject.exception.ResourceNotFoundException;
import com.myProject.repository.CodeRepository;
import com.myProject.service.CodeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CodeServiceImpl implements CodeService {
//    private final static String ALPHA_NUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    private final static Integer CODE_MAX_LENGTH = 6;

    private final AppProperties appProperties;
    private final CodeRepository codeRepository;
    private final ModelMapper modelMapper;

    public CodeServiceImpl(AppProperties appProperties, CodeRepository codeRepository, ModelMapper modelMapper) {
        this.appProperties = appProperties;
        this.codeRepository = codeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CodeDto create(MessageDto messageDto) {
        Message message = modelMapper.map(messageDto, Message.class);
        String generatedCode = generateCode();
        Code code = new Code();
        code.setVerificationCode(generatedCode);
        code.setMessage(message);
        Code persistedCode = codeRepository.save(code);

        return modelMapper.map(persistedCode,CodeDto.class);
    }

    @Override
    public void validateCode(String code) {
        if (!codeRepository.existsByVerificationCode(code)){
            throw new ResourceNotFoundException("Verification code not found");
        }
    }

    @Override
    public void deleteByCode(String verificationCode) {
        Code code = codeRepository.findByVerificationCode(verificationCode).orElseThrow(
                () -> new ResourceNotFoundException("Verification verificationCode not found")
        );

        codeRepository.delete(code);
    }


    private String generateCode() {
        String code = "";
        for (int i = 0; i < appProperties.getCodeMaxLength(); i++) {
            char c = appProperties.getAlphaNumeric().charAt(generateRandomNumber(0, appProperties.getAlphaNumeric().length()));
            String temp = String.valueOf(c);
            code = code.concat(temp);
        }
        return code;
    }

    @Override
    public List<CodeDto> findAll() {
        List<Code> code = codeRepository.findAll();
        return code.stream()
                .map(c -> modelMapper.map(c, CodeDto.class))
                .collect(Collectors.toList());
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
