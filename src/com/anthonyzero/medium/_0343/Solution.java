package com.anthonyzero.medium._0343;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<Integer,Integer> memery = new HashMap<>();
    public int integerBreak(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memery.containsKey(n)) {
            return memery.get(n);
        }
        int maxnum = 0;
        //最优子结构
        for (int i = 1; i < n; i++) { // 1到n-1
            //不分裂i * (n-i) 和分裂i * integerBreak(n - i)的情况
            maxnum = Math.max(maxnum, Math.max(i * (n-i), i * integerBreak(n - i)));
        }
        memery.put(n, maxnum);
        return maxnum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().integerBreak(10));
    }

}
