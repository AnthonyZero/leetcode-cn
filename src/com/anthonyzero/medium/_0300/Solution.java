package com.anthonyzero.medium._0300;

public class Solution {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length]; //dp[i] = max(dp[i], dp[j] + 1) nums[i] > nums[j]
        for(int i = 0; i < dp.length; i++) {
            dp[i] = 1; //初始化长度为1
        }
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Integer.max(dp[i], dp[j] + 1);
                }
            }
            result = Integer.max(result, dp[i]);
        }
        //return dp[nums.length - 1]; //错误返回
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        System.out.println(new Solution().lengthOfLIS(nums));
    }
}
