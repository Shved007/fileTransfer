package com.myProject.web.advice.rest;

import com.myProject.config.AppProperties;
import com.myProject.dto.*;
import com.myProject.service.MessageService;
import com.myProject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/messages")
public class MessageResource {
    private final AppProperties appProperties;
    private final MessageService messageService;
    private final UserService userService;

    public MessageResource(AppProperties appProperties, MessageService messageService, UserService userService) {
        this.appProperties = appProperties;
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createMessage(@RequestBody @Valid SendMessageRequestDto smd){
        UserDto userDto = userService.create(smd.getUserDto());
        SendMessageResponseDto sendMessageResponseDto = messageService.create(smd.getMessageDto(), userDto, smd.getFiles());
        return new ResponseEntity<>(sendMessageResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> readMessage(@RequestParam("code") String code){
        ReadMessageDto messageDto = messageService.readMessage(code);
        return new ResponseEntity<>(messageDto,HttpStatus.OK);
    }

}
