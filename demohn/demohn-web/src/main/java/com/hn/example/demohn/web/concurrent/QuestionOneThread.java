package com.hn.example.demohn.web.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuestionOneThread extends Thread {
    private static AtomicInteger nums = new AtomicInteger(0);
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
    public void run()  {
        try {
            run3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run1() throws InterruptedException{
        synchronized (LOCK) {
            while (total<=100) {
                if (total%4 != order) {
                    LOCK.wait();
                } else {
                    System.out.println("run1==>线程"+str+"："+total);
                    total++;
                    LOCK.notifyAll();
                }

            }
        }
    }
    private void run2(){
        while (nums.get()<=100) {
            if (nums.get()% 4 == order) {
                System.out.println("run2==>线程:"+nums.intValue());
                nums.incrementAndGet();
            }
        }
    }
    private void run3() throws InterruptedException{
        lock.lock();
        while (total<=100) {
            if (total%4 != order) {
                condition.await();
            } else {
                System.out.println("run3==>线程"+str+"："+total);
                total++;
                condition.signalAll();
            }

        }
        lock.unlock();
    }
}
