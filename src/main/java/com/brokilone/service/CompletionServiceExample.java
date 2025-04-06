package com.brokilone.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class CompletionServiceExample {
    private static Executor executor = Executors.newFixedThreadPool(10);

    private CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

    public void testCompletion() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            completionService.submit(() -> {
                try {
                    Thread.sleep(1000);
                    return taskNumber;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    throw e;
                }
            });
        }
        int count = 10;
        while (count > 0) {
            Future<Integer> result = completionService.poll();
            if (result != null) {
                if (result.isDone()) {
                    System.out.println("Task " + result.get() + " completed");
                }
                count--;
            }
        }
    }
}
