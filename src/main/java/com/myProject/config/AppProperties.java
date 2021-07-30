package com.myProject.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@Setter
@ConfigurationProperties(prefix = "my-property")
public class AppProperties {
    private String ggs;
    private String hello;

    private String alphaNumeric;

    private int codeMaxLength;

    @Getter
    @Setter
   public static class Weather {
        private String url;

        private String apiKey;
    }

}
