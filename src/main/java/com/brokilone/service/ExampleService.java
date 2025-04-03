package com.brokilone.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ExampleService {
    private final AsyncService firstAsyncService;
    private final AsyncService secondAsyncService;

    public ExampleService(AsyncService firstAsyncService, AsyncService secondAsyncService) {
        this.firstAsyncService = firstAsyncService;
        this.secondAsyncService = secondAsyncService;
    }

    @Async
    public void asyncMethodVoid() {
        System.out.println("Execute async method " + Thread.currentThread().getName());
    }

    @Async
    public Future<String> asyncMethodFuture() {
        System.out.println("Execute async method " + Thread.currentThread().getName());

        try {
            Thread.sleep(3);
            return new AsyncResult<>("test_answer");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public CompletableFuture<Integer> composeResponses() {
        CompletableFuture<Integer> firstFuture = firstAsyncService.asyncGetData();
        CompletableFuture<Integer> secondFuture = secondAsyncService.asyncGetData();
        return firstFuture.thenCompose(firstValue ->
                secondFuture.thenApply(secondValue -> firstValue + secondValue));
    }

}
