package com.anthonyzero.medium._0695;

public class Solution {

    private int[][] d = {{1,0}, {-1, 0}, {0, 1}, {0, -1}}; //4个方位
    private boolean[][] visited; //访问标识
    private int res; //结果
    private int m,n; //行 列大小
    private int count = 0; //每次深度搜索完之后的岛屿面积
    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                //从每一个点开始
                count = 0;
                dfs(grid, i, j);
                res = Integer.max(res, count); //每次更新最大值结果
            }
        }
        return res;
    }

    //深度优先搜索
    private void dfs(int[][] grid, int x, int y) {
        if(!visited[x][y] && grid[x][y] == 1) {
            //此点是陆地且未访问过 面积 + 1
            count++;
            visited[x][y] = true;
            //继续搜索下去 4个方位
            for(int i = 0; i < d.length; i++) {
                int newX = x + d[i][0];
                int newY = y + d[i][1];
                if(isArea(newX, newY)) {
                    dfs(grid, newX, newY);
                }
            }
        }
    }

    //判断是否是合法坐标
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(new Solution().maxAreaOfIsland(grid));
    }
}
