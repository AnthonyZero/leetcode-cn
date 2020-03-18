package com.anthonyzero.medium._0062;

public class Solution {

    private int[][] memery;
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
    }

    public static void main(String[] args) {
        System.out.println(new Solution().uniquePaths(7, 3));
    }
}
