package com.anthonyzero.medium._0474;

import java.util.HashMap;
import java.util.Map;

public class Solution {

      //递归的方法 超时
//    private String[] strs = null;
//    private Map<String,Integer> cache = new HashMap<>();
//    public int findMaxForm(String[] strs, int m, int n) {
//        this.strs = strs;
//        //初始值为0 从第一个字符串开始
//        return findMax(0,0, m, n);
//    }
//
//    public int findMax(int maxValue, int index, int m, int n) {
//        //递归完了
//        if(index == strs.length) {
//            return maxValue;
//        }
//        //没有资源了
//        if(m < 0 && n < 0) {
//            return maxValue;
//        }
//        String key = maxValue + "," + index + "," + m + "," + n;
//        if(cache.containsKey(key)) {
//            return cache.get(key);
//        }
//        int value = 0;
//        Map<Integer,Integer> map = countZeroOne(strs[index]);
//        if(map.get(0) <= m && map.get(1) <= n) {
//            //满足分配 选择分配与否 看最大值
//            value = Integer.max(findMax(maxValue + 1, index + 1, m - map.get(0), n - map.get(1)),
//                    findMax(maxValue, index + 1, m, n));
//        } else {
//            value = findMax(maxValue, index + 1, m, n);
//        }
//        cache.put(key, value);
//        return value;
//    }


    //dp[index][i][j]表示在strs中从只考虑0到index的字符串，并且使用i个0和j个1能得到的最优解。
    //
    //状态转移方程为：dp[index][i][j] = max(dp[index-1][i][j], dp[index-1][i-?][j-?]+1)
    //结果为： dp[len][m][n]
    public int findMaxForm(String[] strs, int m, int n) {
        int dp[][][] = new int[strs.length + 1][m + 1][n + 1];
        //第0行 没有字符串默认dp值都是0
        for(int index = 1; index <= strs.length; index ++) {
            //从1开始 防止index-1越界
            Map<Integer,Integer> map = countZeroOne(strs[index - 1]);
            for(int i = 0; i <= m; i++) {
                for(int j = 0; j <= n; j++) {
                    dp[index][i][j] = dp[index - 1][i][j];

                    if(i >= map.get(0) && j >= map.get(1)) {
                        //满足分配条件
                        dp[index][i][j] = Integer.max(dp[index - 1][i][j],
                                    dp[index - 1][i - map.get(0)][j - map.get(1)] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private Map<Integer,Integer> countZeroOne(String str) {
        int zeroNum = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '0') {
                zeroNum++;
            }
        }
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, zeroNum);
        map.put(1, str.length() - zeroNum);
        return map;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"10", "0001", "111001", "1", "0"};
        System.out.println(new Solution().findMaxForm(arr, 5, 3));
    }
}
