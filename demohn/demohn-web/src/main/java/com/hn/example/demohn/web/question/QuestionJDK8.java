package com.hn.example.demohn.web.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyb
 * @Date: 2019/8/16
 * @description
 */
public class QuestionJDK8 {
    public static void main(String[] args) {
        List<String> array =new ArrayList<>(5);
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        Map<String,String> data = new HashMap<>(16);
        List<String > array2 = new ArrayList<>();
        array.forEach(v->{
            data.put(v,v);
            array2.add(v);
        });
        array.forEach(System.out::println);
        System.out.println(array2.toString());

    }
}
