package com.jiaxiang.portal.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class Solution {
    public static void main(String[] args) {

        // ThreadPoolExecutor pool1 = new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Executor executor = Executors.newFixedThreadPool(3);
        executor.execute(new T1());
        executor.execute(new T2());
        executor.execute(new T3());

        // executor.shutdown();
    }

    // 不需要原子变量。因为保证了num++;非原子操作只有一个线程在执行
    static int count = 1;

    // 三个信号量控制线程之间的通信，以及阻塞顺序
    // 每个线程只获得自己的许可，执行完毕后，释放后面线程的许可来控制执行顺序
    // 让T1先启动所以s1初始有1个许可，
    // 这时候T2和T2获取不到许可会被先阻塞
    static Semaphore s1 = new Semaphore(1);
    static Semaphore s2 = new Semaphore(0);
    static Semaphore s3 = new Semaphore(0);

    // static 为了在主方法不依赖外部类
    static class T1 implements Runnable {
        @Override
        public void run() {
            while (count <= 100) { // 外层不写true，不想让线程完成任务后空循环
                try {
                    s1.acquire();
                    if (count <= 100) { // 内层做doubleCheck 防止先进循环再阻塞导致多打印
                        System.out.println("T1 :" + count++);
                    }
                    s2.release();
                } catch (Exception e) {
                }
            }
        }
    }

    static class T2 implements Runnable {
        @Override
        public void run() {
            while (count <= 100) { // 外层不写true，不想让线程完成任务后空循环
                try {
                    s2.acquire();
                    if (count <= 100) { // 内层做doubleCheck 防止先进循环再阻塞导致多打印
                        System.out.println("T2 :" + count++);
                    }
                    s3.release();
                } catch (Exception e) {
                }
            }
        }

    }

    static class T3 implements Runnable {
        @Override
        public void run() {
            while (count <= 100) { // 外层不写true，不想让线程完成任务后空循环
                try {
                    s3.acquire();
                    if (count <= 100) { // 内层做doubleCheck 防止先进循环再阻塞导致多打印
                        System.out.println("T3 :" + count++);
                    }
                    Thread.sleep(1000);
                    s1.release();
                } catch (Exception e) {
                }
            }
        }
    }
}