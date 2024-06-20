package com.example.config;

import com.example.aspect.LogRequestAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfiguration {

    private final LogProperties logProperties;

    @Bean
    public LogRequestAspect logRequestAspect() {
        return new LogRequestAspect(logProperties.getLevel(), logProperties.getFormat());
    }
}
