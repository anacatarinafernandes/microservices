package com.anafernandes.catalog.config;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RetryConfiguration {

    @Autowired
    private RetryRegistry retryRegistry;

    @Bean
    public Retry retryWithCustomConfig() {
        RetryConfig customConfig = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.ofSeconds(2))
                .build();

        return retryRegistry.retry("customRetryConfig", customConfig);
    }
}
