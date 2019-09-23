package com.hn.example.demohn.web.concurrent;


/**
 * 按照次序输出一串字符串alibaba
 */
public class Question1 {
    public static void main(String[] args) {
        QuestionOneThread a= new QuestionOneThread("a",0);
        QuestionOneThread b= new QuestionOneThread("b",1);
        QuestionOneThread c= new QuestionOneThread("c",2);
        QuestionOneThread d= new QuestionOneThread("d",3);

        try {
            a.start();
            b.start();
            c.start();
            d.start();

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void run(){
        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 1");
            }
        }, "T1");
        final Thread t2 = new Thread(new Runnable() {
            public void run() {

                try {
                    t1.join();
                    System.out.println(Thread.currentThread().getName() + " run 2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");
        final Thread t3 = new Thread(new Runnable() {
            public void run() {

                try {
                    t2.join();
                    System.out.println(Thread.currentThread().getName() + " run 3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T3");
        t1.start();
        t2.start();
        t3.start();

    }

}
