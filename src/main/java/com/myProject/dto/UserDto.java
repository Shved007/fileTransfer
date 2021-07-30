package com.myProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myProject.entity.Message;
import lombok.Data;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    private Long id;

    @NotEmpty
    @JsonProperty("first_name")
    private String firstName;

    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;

}
