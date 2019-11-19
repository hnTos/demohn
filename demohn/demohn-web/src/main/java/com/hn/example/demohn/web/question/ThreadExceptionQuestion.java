package com.hn.example.demohn.web.question;

import java.util.concurrent.*;

/**
 * @Author: liyb
 * @Date: 2019/8/28
 * @description
 */
public class ThreadExceptionQuestion extends Thread implements Thread.UncaughtExceptionHandler{


    public static void main(String[] args) {


        //MyUncaughtExceptionHandle myUncaughtExceptionHandle = new MyUncaughtExceptionHandle();
        ThreadFactory factory = new HandleThreadFactory();
        ExecutorService exec = new ThreadPoolExecutor(1,3,100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024),factory,new ThreadPoolExecutor.AbortPolicy());
        Thread thread = factory.newThread(new ThreadExceptionRunner());
        //System.out.println("创建线程--------------------");
        Long count =  ((ThreadPoolExecutor)exec).getTaskCount();
        System.out.println("main线程--------------------"+Thread.currentThread().getId());
        for (int i=0;i<1;++i) {
            exec.execute(thread);
        }
        exec.shutdownNow();
        //System.out.println("--------------------");
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("current thread id"+Thread.currentThread().getId());
        System.out.println("throwable thread id"+Thread.currentThread().getId());
        // System.out.println("caught " + e);
        //System.out.println("自定义异常处理 thread Id:" + t.getId()+"current thread:" +Thread.currentThread().getId());
    }
}



class HandleThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {

        //System.out.println("current thread id"+Thread.currentThread().getName());
       // System.out.println("create thread t");
        //System.out.println("set uncaughtException for t"+" id:" +r.getId());
        Thread t = new Thread(r);
       // System.out.println("set uncaughtException for t"+" id:" +t.getId());
        t.setUncaughtExceptionHandler(new ThreadExceptionQuestion());
        return t;
    }


}
