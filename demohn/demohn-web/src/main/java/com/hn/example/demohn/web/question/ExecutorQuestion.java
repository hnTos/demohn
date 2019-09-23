package com.hn.example.demohn.web.question;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: liyb
 * @Date: 2019/7/2
 * @description
 */
public class ExecutorQuestion {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i=0;i<10;++i) {
            final int index =i;
            fixedThreadPool.execute(()->{
                if (index ==5) {
                    throw new IllegalStateException("Error");
                }
                System.out.println(Thread.currentThread() +"----"+index);
            });
        }
        /*for (int i=0;i<10;++i) {
            final int index =i;
            singleThreadExecutor.execute(()->{
                if (index ==5) {
                    throw new IllegalStateException("Error1");
                }
                System.out.println(Thread.currentThread() +"----"+index);
            });
        }*/
        /*ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        System.out.println("---------------newSingleThreadExecutor--------------");
        for(int i = 0; i < 10; i++) {
            final int index = i;
            newSingleThreadExecutor.execute(()->{
                if(index == 20) {
                    throw new IllegalStateException("Error");
                }
                System.out.println(Thread.currentThread() + ".."+index);
            });
        }*/
    }
}
