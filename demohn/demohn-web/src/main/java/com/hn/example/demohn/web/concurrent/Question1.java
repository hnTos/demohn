package com.hn.example.demohn.web.concurrent;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 按照次序输出一串字符串alibaba
 */
public class Question1 {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(4,5,100, TimeUnit.MILLISECONDS,new java.util.concurrent.LinkedBlockingQueue<>(1024),new ThreadPoolExecutor.AbortPolicy());
        pool.execute(new QuestionOneThread("a",0));
        pool.execute(new QuestionOneThread("b",1));
        pool.execute(new QuestionOneThread("c",2));
        pool.execute(new QuestionOneThread("d",3));
        pool.shutdown();
        // 设定最大重试次数
        /*try {
            // 等待 60 s
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("============= shutdownNow");
                // 调用 shutdownNow 取消正在执行的任务
                pool.shutdownNow();
                // 再次等待 60 s，如果还未结束，可以再次尝试，或者直接放弃
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("线程池任务未正常执行结束");
                }
            }
        } catch (InterruptedException ie) {
            // 重新调用 shutdownNow
            pool.shutdownNow();
        }*/
        /*new QuestionOneThread("a",0).start();
        new QuestionOneThread("b",1).start();
        new QuestionOneThread("c",2).start();
        new QuestionOneThread("d",3).start();*/

    }
    public void test(){
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("多线程有序打印出0~100的线程池").build();
        ExecutorService pool = new ThreadPoolExecutor(3,5,100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),factory,new ThreadPoolExecutor.AbortPolicy());
        CurrentQuestion question = new CurrentQuestion();
        Integer total = 3;
        pool.execute(question.new Mythread1("Thread A", 0));
        pool.execute(question.new Mythread1("Thread B", 1));
        pool.execute(question.new Mythread1("Thread C", 2));
    }
}


