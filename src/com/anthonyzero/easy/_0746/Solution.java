package com.anthonyzero.easy._0746;

public class Solution {

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2; i <= cost.length; i++) {
            dp[i] = Integer.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    public static void main(String[] args) {
        int[] cost = new int[]{10, 15, 20};
        System.out.println(new Solution().minCostClimbingStairs(cost));
    }
}
