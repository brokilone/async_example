package com.brokilone.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.CompletableFuture;

public class SecondAsyncService implements AsyncService {

    @Async
    @Override
    public CompletableFuture<Integer> asyncGetData() {
        System.out.println("Execute async method " + Thread.currentThread().getName());
        return new AsyncResult<>(2).completable();
    }
}
