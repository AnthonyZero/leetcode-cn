package com.anthonyzero.medium._0064;

import java.util.Arrays;

public class Solution {

//    private int m;
//    private int n;
//    private int[][] memery;
//    public int minPathSum(int[][] grid) {
//        if(grid == null || grid.length == 0) {
//            return 0;
//        }
//        m = grid.length;
//        n = grid[0].length;
//        memery = new int[m][n];
//        for(int i = 0; i < m; i++) {
//            Arrays.fill(memery[i], -1);
//        }
//        return minPathSum(grid, 0, 0);
//    }
//
//    private int minPathSum(int[][] grid, int x, int y) {
//        if (x == m - 1 && y == n -1) {
//            //到达了终点
//            return grid[x][y];
//        }
//        if (memery[x][y] != -1) {
//            return memery[x][y];
//        }
//        if (x + 1 == m || y + 1 == n) {
//            //当前位置到了边界上
//            if (x + 1 == m) {
//                //到了最后一行,取值结果为 当前数字 + 右边的路径和
//                memery[x][y] = grid[x][y] + minPathSum(grid, x, y + 1);
//            }
//            if (y + 1 == n) {
//                //到了最后一列，取值结果为 当前数字 + 下边的路径和
//                memery[x][y] = grid[x][y] + minPathSum(grid, x + 1, y);
//            }
//        } else {
//            //看两个方向路径和 取最小值
//            memery[x][y] = Integer.min(grid[x][y] + minPathSum(grid, x, y + 1),
//                    grid[x][y] + minPathSum(grid, x + 1, y ));
//        }
//        return memery[x][y];
//    }

    // 动态规划 动态转移方程为：dp[i][j] = min(dp[i-1][j] + dp[i][j-1]) + grid[i][j]
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++) {
            //初始第一列
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int i = 1; i < n; i++) {
            //初始化第一行
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = Integer.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(new Solution().minPathSum(grid));
    }
}
