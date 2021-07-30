package com.myProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
public class SendMessageRequestDto {

    @NotNull
    @JsonProperty("user")
    private UserDto userDto;

    @NotNull
    @JsonProperty("message")
    private MessageDto messageDto;

    @JsonProperty("files")
    private Set<FileDto> files;

}
