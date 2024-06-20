package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "log")
public class LogProperties {
    private String format;
    private String level;
}
