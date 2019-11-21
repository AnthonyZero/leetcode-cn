package com.anthonyzero.easy._0026;

import java.util.Arrays;

public class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 1; //待修改的指针
        int j = 1; //查找目标值（替换值）的指针
        int compapre = nums[0];
        while(j < nums.length) {
            if (nums[j] != compapre) {
                //不相等 替换
                nums[i] = nums[j];
                compapre = nums[i];
                i++;
            }
            j++;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(new Solution().removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
