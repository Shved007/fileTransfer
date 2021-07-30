package com.myProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CodeDto {
    private Long id;

    @NotEmpty
    @JsonProperty("verification_code")
    private String verificationCode;

}
