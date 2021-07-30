package com.myProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageResponseDto {
    @JsonProperty("code")
    private CodeDto codeDto;

    @JsonProperty("message")
    private MessageDto messageDto;
}
