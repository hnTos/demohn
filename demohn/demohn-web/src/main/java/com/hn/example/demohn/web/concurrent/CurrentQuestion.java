package com.hn.example.demohn.web.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author: liyb
 * @Date: 2019/7/1
 * @description
 */
public class CurrentQuestion {

        private static Integer numsB = 1;

        private static String LOCK = "lock";

       class Mythread1 implements Runnable{
            /**
             * 线程名
             */
            private String name;
            /**
             * 次序
             */
            private Integer order;

            public Mythread1(String name,Integer order) {
                this.name = name;
                this.order = order;
            }

            @Override
            public void run(){
                synchronized (LOCK){
                    while(numsB<=100) {
                        if(numsB %3 != order){
                            try {
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(name+":"+numsB);
                            numsB++;
                            LOCK.notifyAll();
                        }
                    }
                }

            }
       }

    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("多线程有序打印出0~100的线程池").build();
        ExecutorService pool = new ThreadPoolExecutor(3,5,100, TimeUnit.MILLISECONDS, (BlockingQueue<Runnable>) new LinkedBlockingQueue<Runnable>(1024),factory,new ThreadPoolExecutor.AbortPolicy());
        CurrentQuestion question = new CurrentQuestion();
        pool.execute(question.new Mythread1("Thread A", 1));
        pool.execute(question.new Mythread1("Thread B", 2));
        pool.execute(question.new Mythread1("Thread C", 0));
        try {
            pool.shutdown();
        } catch (Exception e) {

        }

    }
}
