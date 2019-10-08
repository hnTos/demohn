package com.hn.example.demohn.web.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author: liyb
 * @Date: 2019/7/1
 * @description 多线程顺序输出0~100
 */
public class CurrentQuestion {

        private static String LOCK = "lock";
        
        private static Integer total =0;

        private static Lock lock = new ReentrantLock();

        private static Condition condition = lock.newCondition();
        
        private static AtomicInteger nums = new AtomicInteger(0);

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
                run1();
            }
       }
        /**
        * 使用synchronized
        */
         private void run1(){
                synchronized (LOCK){
                    while (total<=100) {
                        if (total%4 == order) {
                            System.out.println("线程"+str+"："+total);
                            total++;
                            LOCK.notifyAll();
                        } else  {
                            try {
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
          }
        
        /**
        * 使用AtomicInteger
        */
        private void run3(){
                lock.lock();
                while (total<=100) {
                    if (total%4== order) {
                        System.out.println("线程"+str+"："+total);
                        total++;
                        condition.signalAll();
                    } else {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                lock.unlock();

        }
        /**
        * 使用lock
        */
        private void run2(){
                while (nums.get()<=100) {
                    Integer next = nums.get();

                    if (next% 4 == order) {
                        System.out.println("线程:"+nums.intValue());
                        nums.incrementAndGet();
                    }

                }
    }

    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("多线程有序打印出0~100的线程池").build();
        ExecutorService pool = new ThreadPoolExecutor(3,5,100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024),factory,new ThreadPoolExecutor.AbortPolicy());
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
