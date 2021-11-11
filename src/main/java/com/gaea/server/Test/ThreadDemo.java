package com.gaea.server.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//多个线程同时执行一个请求
public class ThreadDemo {

    public static void main(String[] args) {
        ThreadDemo test = new ThreadDemo();
        test.runThread();
    }

    boolean finalFlg = false;

    CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    private void runThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(createThread(i));
        }
    }

    private Thread createThread(int i) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    System.out.println("Thread:" + Thread.currentThread().getName() + "准备完毕,time:" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("name" + i);
        return thread;
    }


}
