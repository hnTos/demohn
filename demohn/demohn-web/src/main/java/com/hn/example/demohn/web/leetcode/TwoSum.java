package com.hn.example.demohn.web.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] num,int target) {
        Map<Integer,Integer> cache = new HashMap<>();
        if (num.length<2) {
            throw new IllegalArgumentException("当前数组长度小于2");
        }
        for (int i=0;i<num.length;++i) {
            if (cache.containsKey(target - num[i])) {
                return new int[]{cache.get(num[i]),i};
            }
            cache.put(num[i],i);
        }
        throw new IllegalArgumentException("未找到当前数组之和为target");
    }
}
