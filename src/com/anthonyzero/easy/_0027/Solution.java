package com.anthonyzero.easy._0027;

import java.util.Arrays;

public class Solution {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0; //  待修改位置
        int j = 0; //寻找后面的元素来替代 前面目标值
        while(j < nums.length && i < nums.length) {
            if (nums[i] == val) {
                if (nums[j] == val) {
                    j++;
                } else {
                    //找到了 交换
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        System.out.println(new Solution().removeElement(nums, 2));
        System.out.println(Arrays.toString(nums));
    }
}
