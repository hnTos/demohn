package com.hn.example.demohn.web.bole;

import java.util.*;

/**
 * @Author: liyb
 * @Date: 2019/8/26
 * @description 有一个 String 类型数组 arr = { "a", "b", "d", "d", "a", "d", "a", "e", "d", "c" }，
 * 请编码实现统计该数组中字符重复次数并由多到少的顺序对 a,b,c,d,e 重新排序输出。
 */
public class Question2 {
    public static void main(String[] args){
        String[] arr = new String[]{ "a", "b", "d", "d", "a", "d", "a", "e", "d", "c" };
        execute(arr);
    }
    public static void execute(String[] arr){
        if (arr==null || arr.length <1){
            return;
        }
        Map<String,Integer> sortedMap = new HashMap();
        for (int i = 0;i<arr.length;++i) {
            Integer count = 1;
            for (int j= i+1;j<arr.length;++j) {
                if (arr[i].equals(arr[j])){
                    ++count;
                }
            }
            if (!sortedMap.containsKey(arr[i])){
                sortedMap.put(arr[i],count);
            }


        }
        sort(sortedMap);

    }
    public static void sort(Map map){
        List<Map.Entry<String, Integer>>  counts = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        /*Collections.sort(counts, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()-o1.getValue());
            }
        });*/
        //counts.sort((arg0, arg1)->{return arg1.getValue().compareTo(arg0.getValue());});
        counts.stream().sorted((arg0, arg1)->{return arg1.getValue().compareTo(arg0.getValue());});
        counts.forEach(entry->System.out.println(entry.getKey()+"…………"+entry.getValue()));
    }
}
