package com.jiaxiang.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Slf4j
@Component
public class AsyncTaskExecutor {

    private final ThreadPoolTaskExecutor executor;

    public AsyncTaskExecutor(@Qualifier("asyncExecutor") ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    /**
     * 无返回值异步执行
     */
    public void runAsync(Runnable task) {
        CompletableFuture.runAsync(() -> {
            try {
                task.run();
            } catch (Exception e) {
                log.error("异步任务执行异常：", e);
            }
        }, executor);
    }

    /**
     * 有返回值异步执行
     */
    public <T> CompletableFuture<T> supplyAsync(Supplier<T> task) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return task.get();
            } catch (Exception e) {
                log.error("异步任务执行异常：", e);
                throw new RuntimeException(e);
            }
        }, executor);
    }

//    模拟使用异步地方
//    public void handleSomethingAsync() {
//        asyncTaskExecutor.runAsync(() -> {
//            // 模拟一个耗时任务
//            try {
//                Thread.sleep(5 * 1000);
//                System.out.println("异步任务执行完毕！");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    public void handleAsyncWithResult() {
//        asyncTaskExecutor.supplyAsync(() -> {
//            // 模拟任务
//            try {
//                Thread.sleep(10 * 1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return "异步结果数据";
//        }).thenAccept(result -> {
//            System.out.println("处理异步返回值：" + result);
//        });
//    }
}