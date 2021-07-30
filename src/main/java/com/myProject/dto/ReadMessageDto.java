package com.myProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReadMessageDto {

    @JsonProperty("user")
    private UserDto userDto;

    @JsonProperty("message")
    private MessageDto messageDto;

    @JsonProperty("files")
    private List<FileDto> filesByMessage;
}
