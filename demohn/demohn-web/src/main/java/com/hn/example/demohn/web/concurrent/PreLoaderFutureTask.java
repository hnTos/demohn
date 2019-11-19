package com.hn.example.demohn.web.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: liyb
 * @Date: 2019/9/18
 * @description future使用demo
 */
public class PreLoaderFutureTask {
    private final FutureTask<Long> future = new FutureTask(new Callable<Long>(){
        @Override
        public Long call() throws InterruptedException{
            Thread.sleep(10000);
            return new Long(1234);
        }
    });
    private final Thread th = new Thread(future);
    public void start(){
        th.start();
    }
    public Long load(){
        new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        return new Long(123);
    }
    public Long get() throws InterruptedException{
        System.out.println("start calculating.......");
        long start = System.nanoTime();
        Long oj = null;
        try {
            oj =  future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if(cause instanceof RuntimeException){
                throw (RuntimeException)cause;
            }else if(cause instanceof Error){
                throw (Error)cause;
            }
        }
        long end = System.nanoTime();
        System.out.println("耗时："+(end-start)+"纳秒得到"+oj);
        return oj;
    }
    public static void main(String []args) throws InterruptedException{
        PreLoaderFutureTask pl = new PreLoaderFutureTask();
        pl.start();
        long begin = System.nanoTime();
        pl.get();
        long end = System.nanoTime();
        System.out.println("等待了"+(end-begin));
    }
}
