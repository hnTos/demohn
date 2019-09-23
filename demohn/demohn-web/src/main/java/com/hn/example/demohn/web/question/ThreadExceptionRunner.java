package com.hn.example.demohn.web.question;

/**
 * @Author: liyb
 * @Date: 2019/8/28
 * @description
 */
public class ThreadExceptionRunner implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("error !!!!");
    }

}
