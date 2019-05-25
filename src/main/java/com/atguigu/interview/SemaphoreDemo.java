package com.atguigu.interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author rociss
 * @version 1.0, on 16:08 2019/5/25.
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        //模拟3个车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i <6 ; i++) {//模拟6个车

            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t get 车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t 3s 后，release 车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }

}












