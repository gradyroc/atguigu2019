package com.atguigu.interview;

import java.util.concurrent.CountDownLatch;

/**
 * @author rociss
 * @version 1.0, on 15:20 2019/5/25.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " \t 国，被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEachCountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t close the door,秦国一统天下");
        System.out.println(CountryEnum.ONE.getRetMessage());
    }
}
