package com.brokilone;

import com.brokilone.service.CompletionServiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class Application {

    @Autowired
    public CompletionServiceExample serviceExample;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @EventListener(value = ApplicationReadyEvent.class)
    public void test() throws ExecutionException, InterruptedException {
        serviceExample.testCompletion();
    }


}
