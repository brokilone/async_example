package com.brokilone.configuration;

import com.brokilone.service.AsyncService;
import com.brokilone.service.ExampleService;
import com.brokilone.service.FirstAsyncService;
import com.brokilone.service.SecondAsyncService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class SpringAsyncConfig {

    @Bean
    public ExampleService exampleService(AsyncService firstAsyncService, AsyncService secondAsyncService) {
        return new ExampleService(firstAsyncService, secondAsyncService);
    }

    @Bean
    public AsyncService firstAsyncService() {
        return new FirstAsyncService();
    }

    @Bean
    public AsyncService secondAsyncService() {
        return new SecondAsyncService();
    }
}
