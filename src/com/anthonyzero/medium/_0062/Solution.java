package com.anthonyzero.medium._0062;

import java.util.Arrays;

public class Solution {

    /*private int[][] memery;
    //m列 n行
    public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0) {
            return 0;
        }
        memery = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                memery[i][j] = -1;
            }
        }
        return recursionPaths(m, n);
    }

    //m列 n行
    public int recursionPaths(int m, int n) {
        if(m <= 0 || n <= 0) {
            return 0;
        }
        if (m == 1 && n == 1) {
            return 1; //只有一个网格 一条路径
        }
        if(memery[m][n] != -1) {
            return memery[m][n];
        }
        // 两个方向来的 路径加起来就是 路径和
        memery[m][n] = recursionPaths(m - 1, n) + recursionPaths(m, n - 1);
        return memery[m][n];
    }*/

    //m行 n列 跟 m列 n行 结果是相同的
    /*public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0) {
            return 0;
        }
        int dp[][] = new int[m][n];
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1; //设置第1列 为1 只有一条路径
        }
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;  //设置第1行 为1 只有一条路径
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }*/

    //空间优化 由于dp值依赖 左和上的结果 所以两行数据
    public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0) {
            return 0;
        }
        int dp[][] = new int[2][n]; //2行数据
        Arrays.fill(dp[0], 1); //第一行全为1
        dp[1][0] = 1; //第二行 第一个元素也为1
        for(int i = 1; i < m; i++) {
            for(int j= 1; j < n; j++) {
                if (i % 2 == 1) {
                    //修改第二行数据
                    dp[1][j] = dp[0][j] + dp[1][j - 1];
                } else {
                    //修改第一行数据
                    dp[0][j] = dp[1][j] + dp[0][j - 1];
                }
            }
        }
        if (m % 2 == 1) {
            return dp[0][n-1];
        } else {
            return dp[1][n-1];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().uniquePaths(7, 3));
    }
}
