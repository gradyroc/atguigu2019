package com.atguigu.interview.ipc;

import java.util.concurrent.*;

/**
 * @author rociss
 * @version 1.0, on 11:12 2019/5/26.
 *
 * Executor,Executors
 * Executors 是工具类
 *
 * 第4种获得使用java多线程的方式：线程池
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

//        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder()
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
                new ThreadPoolExecutor.CallerRunsPolicy());
        /*
         new ThreadPoolExecutor.CallerRunsPolicy():任务满了时刻，任务退回到调用线程，即main线程
         main	 do business
         pool-1-thread-1	 do business
         */

        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t do business");
                });

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
    public void threadPoolInit(){
//        System.out.println(Runtime.getRuntime().availableProcessors());

//        ExecutorService threadPool= Executors.newFixedThreadPool(5);// 5个线程
//        ExecutorService threadPool= Executors.newSingleThreadExecutor();//1个线程
        ExecutorService threadPool= Executors.newCachedThreadPool();//一个线程池，N个线程
        //模拟10个用户任务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 100; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t do business");
                });

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }


    }
}
