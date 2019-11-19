package com.hn.example.demohn.web.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
* @author liyb
* @description 基于synchronized的生产者消费者简单实现
*/
public class ProducerConsumer {
    private static Integer count = 0;
    private final Integer  FULL  = 10;
    private static String  LOCK  = "LOCK";

    private ReentrantLock reentrantLock = new ReentrantLock();
    //创建两个条件变量，一个为缓冲区非满，一个为缓冲区非空
    private final Condition consumer = reentrantLock.newCondition();
    private final Condition producer = reentrantLock.newCondition();

    class Producer implements Runnable {
        @Override
        public void run() {
            run2();
        }
        void run1(){
            while (true) {
                System.out.println(Thread.currentThread().getName() + "生产者尝试获得锁------" );
                synchronized (LOCK) {
                    System.out.println(Thread.currentThread().getName() + "生产者尝试成功获得锁------" );
                    while (count.equals( FULL)) {
                        try {
                            System.out.println("----------------------当前已满----------------");
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    try {
                        LOCK.notifyAll();
                        System.out.println(Thread.currentThread().getName() + "睡眠：1000 ，目前总共有" + count);
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "睡眠完成：1000 ，目前总共有" + count);

                        System.out.println(Thread.currentThread().getName() + "生产者释放获得锁------" );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "生产者尝试获得锁------失败------" );

            }
        }
        private void run2(){
            while (true) {
               if (reentrantLock.tryLock()){
                    try {
                        while (count .equals(FULL)) {
                            try {
                                System.out.println("----------------当前已满-------------");
                                producer.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        count++;
                        System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //唤醒消费者
                        producer.signalAll();
                    } finally {
                        //释放锁
                        reentrantLock.unlock();
                    }
               }
            }



        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            run2();
        }
        void run1(){
            while (true) {
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            System.out.println("----------------当前为空----------------------------");
                            LOCK.wait();
                        } catch (Exception e) {
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    try {
                        System.out.println(Thread.currentThread().getName() + "睡眠：500 ，目前总共有" + count);
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName() + "睡眠完成：500 ，目前总共有" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LOCK.notifyAll();
                }

            }
        }
        void run2(){
            while (true) {
                if (reentrantLock.tryLock()) {
                    try {
                        while (count == 0) {
                            try {
                                System.out.println("当前为空");
                                consumer.await();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        count--;
                        System.out.println(Thread.currentThread().getName()+ "消费者消费，目前总共有" + count);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        consumer.signalAll();
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        for (Integer i=0;i<4;++i)     {
            new Thread(producerConsumer.new Producer()).start();
        }
        for (Integer i=0;i<2;++i)     {
            new Thread(producerConsumer.new Consumer()).start();
        }

       /* new Thread(producerConsumer.new Consumer()).start();
        for (int i =0;i<20;++i) {
            new Thread(producerConsumer.new Producer()).start();
        }

        new Thread(producerConsumer.new Consumer()).start();
        */
    }
}
