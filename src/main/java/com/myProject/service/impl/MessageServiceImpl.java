package com.myProject.service.impl;

import com.myProject.dto.*;
import com.myProject.entity.Message;
import com.myProject.entity.User;
import com.myProject.exception.ResourceNotFoundException;
import com.myProject.repository.MessageRepository;
import com.myProject.service.CodeService;
import com.myProject.service.FileService;
import com.myProject.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final CodeService codeService;
    private final FileService fileService;

    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper, CodeService codeService, FileService fileService) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.codeService = codeService;
        this.fileService = fileService;
    }

    @Override
    public SendMessageResponseDto create(MessageDto messageDto, UserDto userDto, Set<FileDto> files) {
        User user = modelMapper.map(userDto, User.class);
        Message message = modelMapper.map(messageDto, Message.class);
        message.setUser(user);
        message.setReviewed(false);
        Message persistedMessage = messageRepository.save(message);
        MessageDto persistedMessageDto = modelMapper.map(persistedMessage, MessageDto.class);

        fileService.saveFile(message, files);

        CodeDto codeDto = codeService.create(persistedMessageDto);

        return new SendMessageResponseDto(codeDto, persistedMessageDto);
    }

    @Override
    public ReadMessageDto readMessage(String code) {
        codeService.validateCode(code);
        Message message = messageRepository.findByCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Message not found by code:" + code)
        );
        List<FileDto> filesByMessage = fileService.getFilesByMessage(message.getId());
        message.setReviewed(true);
        messageRepository.save(message);
        return new ReadMessageDto(modelMapper.map(message.getUser(), UserDto.class), modelMapper.map(message, MessageDto.class), filesByMessage);
    }
}
