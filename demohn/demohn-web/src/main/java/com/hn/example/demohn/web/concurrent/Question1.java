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
        pool.shutdownNow();

    }
    public void test(){
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("多线程有序打印出0~100的线程池").build();
        ExecutorService pool = new ThreadPoolExecutor(3,5,100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),factory,new ThreadPoolExecutor.AbortPolicy());
        CurrentQuestion question = new CurrentQuestion();
        pool.execute(question.new Mythread1("Thread A", 0));
        pool.execute(question.new Mythread1("Thread B", 1));
        pool.execute(question.new Mythread1("Thread C", 2));
    }
}


