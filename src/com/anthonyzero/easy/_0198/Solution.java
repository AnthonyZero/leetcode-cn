package com.anthonyzero.easy._0198;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<Integer,Integer> memery = new HashMap<>();
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return robChoose(nums, 0);
    }

    public int robChoose(int[] nums, int index) {
        if (index == nums.length - 1) {
            return nums[index];
        }
        if (index == nums.length - 2) {
            return Math.max(nums[index], nums[index + 1]);
        }
        if (memery.containsKey(index)) {
            return memery.get(index);
        }
        int total = Math.max(nums[index] + robChoose(nums, index + 2),
                robChoose(nums, index + 1));
        memery.put(index, total);
        return total;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(new Solution().rob(nums));
    }
}
