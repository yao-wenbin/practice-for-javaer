package com.ywb;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author yaowenbin
 * @Date ${DATE}
 */

public class Main {
    static OtherService service = new OtherService();

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> name = service.getName();

        name.thenApply((result) -> {
            return 123;
        });

        System.out.println("call after service");

        // example2();
    }

    private static void example2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": Task 1 started");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": Task 1 completed");
        });

        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": Task 2 started");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": Task 2 completed");
            future.complete("Result");
        });

        CompletableFuture<Void> combinedTask = task1.thenAcceptBothAsync(future, (result1, result2) -> {
            System.out.println(Thread.currentThread().getName() + ": Combined task started");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": Combined task completed");
        });

        combinedTask.get();
    }

}

class OtherService {

    CompletableFuture<String> getId() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture("1");
    }

    CompletableFuture<String> getName() {
        CompletableFuture<String> future = new CompletableFuture<>();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        future.complete("yaowb");
        return future;
    }
}
