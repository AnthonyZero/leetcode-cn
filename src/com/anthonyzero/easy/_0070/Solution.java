package com.anthonyzero.easy._0070;

import java.util.HashMap;
import java.util.Map;

public class Solution {

//    private Map<Integer,Integer> map = new HashMap<>();
//    public int climbStairs(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        if (n == 2) {
//            return 2;
//        }
//        if (map.containsKey(n)) {
//            return map.get(n);
//        } else {
//            //两种选择 要么爬一节 要么爬两节
//            int ways = climbStairs(n - 1) + climbStairs(n - 2);
//            map.put(n, ways);
//            return ways;
//        }
//    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(4));
    }
}
