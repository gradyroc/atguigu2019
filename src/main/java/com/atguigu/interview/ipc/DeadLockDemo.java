package com.atguigu.interview.ipc;

import java.util.concurrent.TimeUnit;

/**
 * @author rociss
 * @version 1.0, on 19:38 2019/5/26.
 * 死锁是指两个或者两个以上的进程在执行过程中
 * 因争夺资源造成的一种互相等待的现象
 * 若无外力干涉，则都无法推进下去
 */

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;


    public HoldLockThread(String lockA, String lockB) {
        lockA = lockA;
        lockB = lockB;
    }


    @Override
    public void run() {
        //吃着碗里
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t 尝试获得："+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //看着锅里
            synchronized (lockB){

                System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB+"\t 尝试获得："+lockA);
            }
        }
    }
}
public class DeadLockDemo {

    public static void main(String[] args) {

        String lockA="lockA";
        String lockB="lockB";
        new Thread(new HoldLockThread(lockA,lockB),"AAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"BBBB").start();
    }
}
