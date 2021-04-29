package com.hn.example.demohn.web.leetcode;

/**
 * @author yuqing
 * @date 2021-04-29
 * @description
 */
public class ClimbStairs {
    public int climbStairs1(int n) {
        int[] dp = new int[n+1];
        dp[0] =1;
        dp[1]= 1;
        for(int i =2;i<=n;++i) {
            dp[i] =dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public int climbStairs2(int n) {
        int round = 1;
        int p=0;
        int q = 0;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = round;
            round = p + q;
        }
        return round;
    }


}
