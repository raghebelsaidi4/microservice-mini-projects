package com.ragheb.registration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "email.key")
@Setter
@Getter
//@Builder
public class EmailKeyDTO {
    private String apiKey;
    private String clientSecret;
}
