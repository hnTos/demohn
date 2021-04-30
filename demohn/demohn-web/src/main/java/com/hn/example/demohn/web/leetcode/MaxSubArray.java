package com.hn.example.demohn.web.leetcode;

/**
 * @author yuqing
 * @date 2021-04-30
 * @description
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums){
        int maxAnds= 0;
        int sum = nums[0];
        for (int x:nums) {
            maxAnds = Math.max(maxAnds+x,x);
            sum = Math.max(sum,maxAnds);
        }
        return sum;

    }
}
