package com.anthonyzero.easy._0035;

public class Solution {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int middle = (left + right) / 2;
            if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{1,3,5,6};
        System.out.println(new Solution().searchInsert(nums, 3));
    }
}
