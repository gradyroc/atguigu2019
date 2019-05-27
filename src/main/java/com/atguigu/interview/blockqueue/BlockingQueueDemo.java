package com.atguigu.interview.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author rociss
 * @version 1.0, on 16:54 2019/5/25.
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<String > blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
//        blockingQueue.add("x"); java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.element());// 检查队首元素
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());java.util.NoSuchElementException
    }
}










