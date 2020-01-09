package com.anthonyzero.easy._0303;


public class Solution {

    class NumArray {
        private int[] data;  //保存前n个数之和
        public NumArray(int[] nums) {
            data = new int[nums.length + 1];
            data[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                data[i + 1] = data[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return data[j + 1] - data[i]; //data(6) - data(0) 就是（0,5）这个区间的和
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray numArray = new Solution().new NumArray(array);
        System.out.println(numArray.sumRange(0, 5));
    }

}
