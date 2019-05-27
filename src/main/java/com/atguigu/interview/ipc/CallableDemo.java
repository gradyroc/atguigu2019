package com.atguigu.interview.ipc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author rociss
 * @version 1.0, on 10:15 2019/5/26.
 */

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("***********come in Callable *************");
        return 1024;
    }
}


public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        Thread t1 = new Thread(futureTask,"AAA");
        t1.start();
       //两个或者多个线程获取同一个futureTask，只执行一次，结果会复用
        //除非用不同的 FutureTask
        Thread t2 = new Thread(futureTask,"AAA");
        t2.start();

        //获取 Callable 线程的计算结果，如果没完成，则阻塞当前获取结果的线程
        //所以推荐get()放到流程的最后
        System.out.println("******result: "+futureTask.get());
    }
}
