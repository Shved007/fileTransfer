package com.myProject.service;

import com.myProject.dto.*;

import java.util.Set;

public interface MessageService {

    SendMessageResponseDto create(MessageDto messageDto, UserDto userDto, Set<FileDto> files);

    ReadMessageDto readMessage(String code);
}
