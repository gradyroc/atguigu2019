package com.atguigu.interview.gc;

/**
 * @author rociss
 * @version 1.0, on 1:01 2019/5/27.
 */
public class HelloGc {

    public static void main(String[] args) throws InterruptedException {

//        System.out.println("***********hello gc");
//        Thread.sleep(Integer.MAX_VALUE);
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("-Xms = "+(totalMemory/(double)1024/1024)+"MB");
        
        System.out.println("-Xmx = "+(maxMemory/(double)1024/1024)+"MB");
    }
}
