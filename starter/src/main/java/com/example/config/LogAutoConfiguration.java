package com.example.config;

import com.example.aspect.LogRequestAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class LogAutoConfiguration {

    @Bean
    public LogRequestAspect logRequestAspect() {
        return new LogRequestAspect();
    }
}
