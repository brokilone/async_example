package com.brokilone.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<Integer> asyncGetData();
}
