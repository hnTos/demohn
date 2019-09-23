package com.hn.example.demohn.web.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author: liyb
 * @Date: 2019/5/31
 * @description
 */
public class MemoryLeak {
    private static AtomicInteger at= new AtomicInteger(0);
    private  static  ThreadLocal<String> localMap =  new ThreadLocal(){};

    public static void main(String [] args) {
        Thread t = new Thread();
        (localMap).set("123");
        System.out.println(localMap.get());

        (localMap).set("321");
        (localMap).set("111");
        System.out.println(localMap.get());
    }
}
