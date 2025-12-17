package com.platform.recommendor.app.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "csvExecutor")
    public Executor csvExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);         // 8 threads
        executor.setMaxPoolSize(16);         // up to 16
        executor.setQueueCapacity(5000);     // tasks queue
        executor.setThreadNamePrefix("CSV-Worker-");
        executor.initialize();
        return executor;
    }
}

