package com.brokilone.configuration;

import com.brokilone.service.AsyncService;
import com.brokilone.service.ExampleService;
import com.brokilone.service.FirstAsyncService;
import com.brokilone.service.SecondAsyncService;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class SpringAsyncConfig implements AsyncConfigurer {


    @Override
    public Executor getAsyncExecutor() {
        return threadPoolTaskExecutor();
    }

    @Bean("executorCustom")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setQueueCapacity(0);
        return executor;
    }

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

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            System.out.println(ex.getMessage());
            System.out.println(method.getName());
            for (Object param : params) {
                System.out.println(param);
            }
        };
    }
}
