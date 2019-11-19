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
    public void run() {
        run2();
    }
    private void run2(){
        while (nums.get()<=100) {
            if (nums.get()% 4 == order) {
                System.out.println("线程:"+nums.intValue());
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
}
