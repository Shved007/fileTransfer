package com.myProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;


@Data
public class MessageDto {

    private Long id;

    @NotEmpty
    @JsonProperty("message_to_send")
    private String messageToSend;

}
