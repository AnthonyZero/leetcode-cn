package com.anthonyzero.medium._0560;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int subarraySum(int[] nums, int k) {
        //连续子数组和 的次数
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, 1); //出现和为0的 一次
        int sum = 0;
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];  //[0,i]的和
            if(map.containsKey(sum - k)) { //表示连续子数组减去连续子数组，必定为连续子数组
                //找到了 有连续子数组和 = k
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum(new int[]{1,1,1}, 2));
        System.out.println(new Solution().subarraySum(new int[]{-1, -1, 1, -1, 1}, 0));
    }
}
