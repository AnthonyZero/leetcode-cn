package com.anthonyzero.easy._0042;

public class Solution {

    public int maxSubArray(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int minArrSub = 0; //前缀和最小值
        int res = nums[0]; //目标最大值
        int sum = nums[0]; //当前所在位置的前缀和
        for(int i = 1; i < nums.length; i++) {
            sum += nums[i];
            minArrSub = Integer.min(sum - nums[i], minArrSub);
            if(minArrSub < 0) {
                res = Integer.max(sum - minArrSub, res);
            } else {
                res = Integer.max(sum, res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(new Solution().maxSubArray(new int[]{-2,-1}));
    }
}
