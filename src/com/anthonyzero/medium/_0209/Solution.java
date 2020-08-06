package com.anthonyzero.medium._0209;

public class Solution {

    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int sum = nums[0]; //当前子数组的和
        int total = 1; //当前子数组长度
        while(i < nums.length) {
            if(sum >= s) {
                if(i == 0) { //第一个元素就符合条件
                    return total;
                } else {
                    minLength = Integer.min(minLength, total);
                }
            } else {
                j += 1;
                while(j < nums.length) { //不断加上nums[j],当和超过s之后，再加无济于事，记录当前子数组最小长度，并将i后移一位继续看和
                    sum += nums[j];
                    total += 1;
                    if(sum >= s) {
                        minLength = Integer.min(minLength, total);
                        break;
                    }
                    j++;
                }
            }
            sum -= nums[i]; //i准备后移一位，同时原先和要减掉一个数，长度同时也要-1
            total -= 1;
            i++;
        }
        if(minLength == Integer.MAX_VALUE) {
            //没有找到满足条件的子数组
            return 0;
        }
        return minLength;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(new Solution().minSubArrayLen(3, new int[]{1,1}));
    }
}
