package com.anthonyzero.easy._0198;

import java.util.HashMap;
import java.util.Map;

public class Solution {

//    private Map<Integer,Integer> memery = new HashMap<>();
//    public int rob(int[] nums) {
//        if (nums.length == 0) {
//            return 0;
//        }
//        if (nums.length == 1) {
//            return nums[0];
//        }
//        return robChoose(nums, 0);
//    }
//
//    public int robChoose(int[] nums, int index) {
//        if (index == nums.length - 1) {
//            return nums[index];
//        }
//        if (index == nums.length - 2) {
//            return Math.max(nums[index], nums[index + 1]);
//        }
//        if (memery.containsKey(index)) {
//            return memery.get(index);
//        }
//        int total = Math.max(nums[index] + robChoose(nums, index + 2),
//                robChoose(nums, index + 1));
//        memery.put(index, total);
//        return total;
//    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length]; //dp[i] 代表考虑偷取0到i房屋的最高金额
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            //是否选择偷取第i个房屋
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(new Solution().rob(nums));
    }
}
