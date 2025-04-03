package com.brokilone.service;

import com.brokilone.configuration.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleServiceTest extends BaseIT {
    @Autowired
    ExampleService exampleService;

    @Test
    void asyncMethodVoid() {
        exampleService.asyncMethodVoid();
    }

    @Test
    void asyncMethodFuture() throws ExecutionException, InterruptedException {
        Future<String> future = exampleService.asyncMethodFuture();
        assertNotNull(future.get());
    }

    @Test
    void composeResponses() {
        CompletableFuture<Integer> completableFuture = exampleService.composeResponses();
        Integer result = completableFuture.join();

        assertNotNull(result);
        assertEquals(3, result);
    }
}