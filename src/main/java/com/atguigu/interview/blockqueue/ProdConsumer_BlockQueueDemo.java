package com.atguigu.interview.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rociss
 * @version 1.0, on 23:10 2019/5/25.
 * 一个初始化为0变量，两个线程交替操作，来5轮
 * 1 、线程   操作   资源类
 * 2、 判断   干活   通知
 * 3、 防止虚假唤醒机制   if 还是while
 */
/*
线程操作资源类
volatile、 CAS、 atomicInteger 、BlockQueue、  线程交互
 */
class BlockResource {
    /*
     *默认开启，进行生产+消费
     */
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public BlockResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {

        String data = null;
        boolean ret;
        //并发情况都用while，不用if
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            ret = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (ret) {
                System.out.println(Thread.currentThread().getName() + "\t inset " + data + " sucess");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t inset " + data + " failed");

            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t boss called stop, flag is false,produce over");
    }

    public void myConsumer() throws Exception {

        String result = null;
        while (flag) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t over 2 second not get the data ,consumer exit");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t consumer queue " + result + " sucess");
        }
    }

    public void stop() throws Exception {
        this.flag = false;
    }

}


public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) {

        BlockResource blockResource = new BlockResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t producer thread start");
            try {
                blockResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "producer").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t consumer thread start");
            try {
                blockResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5s timeOut,boss called the game over !");
        try {
            blockResource.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









