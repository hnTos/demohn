package com.hn.example.demohn.web.concurrent;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * @Author: liyb
 * @Date: 2019/9/18
 * @description 闭锁
 */
public class TestHarnessLatch {
    public long timeTask(int nthreads ,final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nthreads);

        for(int i = 0; i < nthreads; ++i){
            Thread t = new Thread(){
                @Override
                public void run(){
                    try {
                        System.out.println("startGate……await");
                        startGate.await();
                        try{
                            task.run();
                            System.out.println("task……run");
                        }finally{
                            endGate.countDown();
                            System.out.println("endGate……countdown");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }////end for

        long start = System.nanoTime();
        sleep(5000);
        System.out.println("startGate……coundown");

        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end -start;
    }
    
    public static void main(String []args){
        TestHarnessLatch th = new TestHarnessLatch();
        long time = 0;
        try {
            time = th.timeTask(10, new Thread(){
                public void run(){
                    for(int i = 0;i<10000; ++i){}
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("历时"+time+"纳秒");
    }
}
