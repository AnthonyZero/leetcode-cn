package com.anthonyzero.medium._0416;

public class Solution {

    private int[][] memery; //不能为boolean类型 要有个标示为没缓存的 比如返回值为boolean类型时 1和0代表 -1初始值
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            //不能平分 说明不存在两个子集的元素和相等
            return false;
        }
        //从n个数中选择和等于sum/2
        memery = new int[nums.length][sum / 2 + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                memery[i][j] = -1;
            }
        }
        return tryPartition(nums, 0, sum / 2);
    }

    private boolean tryPartition(int[] nums, int index, int sum) {
        if (index == nums.length - 1) {
            return sum == nums[index];
        }
        if (sum < 0) {
            return false;
        }
        if (memery[index][sum] != -1) {
            return memery[index][sum] == 1;
        }
        if (tryPartition(nums, index + 1, sum) ||
                tryPartition(nums, index + 1, sum - nums[index])){
            memery[index][sum] = 1;
            return true;
        } else {
            memery[index][sum] = 0;
            return false;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 5, 5, 5};
        System.out.println(new Solution().canPartition(nums));
    }
}
