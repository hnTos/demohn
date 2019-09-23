package com.hn.example.demohn.web.question;

import java.util.concurrent.LinkedBlockingQueue;

public class Question2{
   /* private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(MAX_SIZE);

    public void producer(Object obj) {
        if (list.size() == MAX_SIZE) {
            System.out.println("队列已满");
        }
        try {
            list.put(obj);
            System.out.println("add one "+obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void consumer(){
        if (list.size() == 0) {
            System.out.println("当前为空");
        }
        try {
             Object obj= list.take();
            System.out.println("consumer one "+obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


}
