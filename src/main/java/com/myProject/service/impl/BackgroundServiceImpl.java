package com.myProject.service.impl;

import com.myProject.entity.Code;
import com.myProject.entity.Message;
import com.myProject.repository.CodeRepository;
import com.myProject.repository.MessageRepository;
import com.myProject.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Фонова робота (Cron Job) - ротоба яка спрацьовує за певним тригером(заданий час\періодичність), без участі користувача
@Slf4j
@Service
public class BackgroundServiceImpl {
    private final MessageRepository messageRepository;
    private final CodeRepository codeRepository;
    private final FileService fileService;

    public BackgroundServiceImpl(MessageRepository messageRepository, CodeRepository codeRepository, FileService fileService) {
        this.messageRepository = messageRepository;
        this.codeRepository = codeRepository;
        this.fileService = fileService;
    }

    @Scheduled(initialDelay = 30 * 1000, fixedDelay = 5 * 60 * 1000)
    public void deleteReviewedMessages() {
        log.debug("[===background Job is Start===]");
        List<Message> messages = messageRepository.findByIsReviewedIsTrue();

//        for (Message m: messages){
//            List<Code> codeList = codeRepository.findByMessageId(m.getId());
//            System.out.println(codeList.size()+ " "+ messages.size());
//        }
        List<Long> messagesIds = messages.stream().map(Message::getId).collect(Collectors.toList());
        List<Code> codeByMessages = codeRepository.findByMessageIdIn(messagesIds);

        codeRepository.deleteAll(codeByMessages);
        fileService.deleteFilesByMessage(messagesIds);
        messageRepository.deleteAll(messages);


    }

}
