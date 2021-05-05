package com.hn.example.demohn.web.leetcode.middle;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuqing
 * @date 2021-05-05
 * @description
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for(int x:nums) {
            numSet.add(x);
        }
        int result = 0;
        for(int num : numSet) {
            if(!numSet.contains(num-1)) {
                int localMax = 1;
                while(numSet.contains(num+1)) {
                    num++;
                    localMax++;
                }
                result = Math.max(localMax,result);
            }

        }
        return result;
    }
}
