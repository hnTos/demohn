package com.hn.example.demohn.web.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuestionOneThread extends Thread {
    private static AtomicInteger nums = new AtomicInteger(0);

    private static ThreadLocal<Integer> local =new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {

            return 0;
        }
    };
    private String str;
    private Integer order;

    private static String LOCK ="lock";

    private static Integer total =0;

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public QuestionOneThread(String str, Integer order){
        this.str = str;
        this.order = order;
    }

    @Override
    public void run() {
        run3();
    }
    private void run2(){
        while (nums.get()<100) {
            Integer next = nums.get();

            if (next% 4 == order) {
                if (next%4==0) {
                    System.out.println("线程"+nums.intValue());
                }
                System.out.print(str);
                nums.incrementAndGet();
            }

        }
    }
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
     * 做不到递增输出
     */
    private void run3(){
       // System.out.println("线程"+str+"："+"trylock");

            if (lock.tryLock()) {
                while (total<=100) {
                    if (total%4== order) {
                       System.out.println("线程"+str+"："+total);
                        total++;
                        condition.signalAll();
                    } else {
                        try {
                           // System.out.println("线程"+str+"："+"await");
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                lock.unlock();

            }else {
               // System.out.println("线程"+str+"："+"trylock fail");
            }

    }
}
