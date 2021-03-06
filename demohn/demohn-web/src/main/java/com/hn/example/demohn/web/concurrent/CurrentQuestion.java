package com.hn.example.demohn.web.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: liyb
 * @Date: 2019/7/1
 * @description 多线程顺序输出0~100
 */
public class CurrentQuestion {

    private static String LOCK = "lock";

    private static Integer total = 0;

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    private static AtomicInteger nums = new AtomicInteger(0);

    class Mythread1 implements Runnable {
        /**
         * 线程名
         */
        private String name;
        /**
         * 次序
         */
        private Integer order;

        private Integer total;


        public Mythread1(String name, Integer order) {
            this.name = name;
            this.order = order;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        @Override
        public void run() {
            run3();
        }

        /**
         * 使用synchronized
         */
        private void run1() {
            synchronized (LOCK) {
                while (total <= 100) {
                    if (total % total == order) {
                        System.out.println("线程" + name + "：" + total);
                        total++;
                        LOCK.notifyAll();
                    } else {
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
        private void run2() {
            while (nums.get() <= 100) {
                Integer next = nums.get();
                if (next % total == order) {
                    System.out.println("线程:" + nums.intValue());
                    nums.incrementAndGet();
                }

            }
        }

        /**
         * 使用lock
         */
        private void run3() {
            lock.lock();
            while (total <= 100) {
                if (total % total == order) {
                    System.out.println("线程" + getName() + "：" + total);
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
    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("多线程有序打印出0~100的线程池").build();
        ExecutorService pool = new ThreadPoolExecutor(3, 5, 100, TimeUnit.MILLISECONDS, (BlockingQueue<Runnable>) new LinkedBlockingQueue<Runnable>(1024), factory, new ThreadPoolExecutor.AbortPolicy());
        CurrentQuestion question = new CurrentQuestion();
        pool.execute(question.new Mythread1("Thread A", 1));
        pool.execute(question.new Mythread1("Thread B", 2));
        pool.execute(question.new Mythread1("Thread C", 0));
        pool.shutdown();
        try {
            // 等待 60 s
            if (!Pool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 调用 shutdownNow 取消正在执行的任务
                pool.shutdownNow();
                // 再次等待 60 s，如果还未结束，可以再次尝试，或者直接放弃
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("线程池任务未正常执行结束");
            }
        } catch (InterruptedException ie) {
            // 重新调用 shutdownNow
            pool.shutdownNow();
        }

    }
}
