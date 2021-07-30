package com.myProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponseDto {

        @JsonProperty("file_name")
        private String fileName;

        @JsonProperty("content_type")
        private String contentType;

        private String url;

}
