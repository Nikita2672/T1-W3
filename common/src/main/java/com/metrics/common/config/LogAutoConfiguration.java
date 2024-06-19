package com.metrics.common.config;

import com.metrics.common.aspect.LogRequestAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class LogAutoConfiguration {

    @Bean
    public LogRequestAspect logRequestAspect() {
        return new LogRequestAspect();
    }
}
