package com.anthonyzero.easy._0053;

public class Solution {

    /**
     * 贪心
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int curMax = nums[0];
        int max = curMax;
        for(int i = 1; i < nums.length; i++) {
            curMax = Integer.max(nums[i], curMax + nums[i]);
            max = Integer.max(curMax, max);
        }
        return max;
    }


    /**
     * nums   2 3 -6 2 4
     * curMax 2 5 -1 2 6
     *  max   2 5  5 5 6
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,-6,2,4};
        System.out.println(new Solution().maxSubArray(arr));
    }

}
