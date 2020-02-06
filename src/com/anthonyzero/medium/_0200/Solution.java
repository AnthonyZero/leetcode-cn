package com.anthonyzero.medium._0200;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private boolean[][] visited;
    private int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m,n; //二维网络的大小

    //判断是否是合法坐标
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
//    public int numIslands(char[][] grid) {
//        if(grid.length == 0) {
//            return 0;
//        }
//        m = grid.length;
//        n = grid[0].length;
//        visited = new boolean[m][n];
//        int res = 0; //结果
//        for(int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == '1' && !visited[i][j]) {
//                    //从陆地开始 且此陆地没被标记过
//                    res++;
//                    //每一次搜索 从i,j出发 就可以把相连的陆地做标记 形成一块岛屿 res++
//                    dfs(grid, i, j);
//                }
//            }
//        }
//        return res;
//    }
//
//    //深度优先搜索 把每一步陆地设置为访问状态 每做一次就找到了一块岛屿（岛屿与岛屿之间没有陆地相连）
//    private void dfs(char[][] grid, int startx, int starty) {
//
//        visited[startx][starty] = true;
//
//        for (int i = 0; i < 4; i++) {
//            //4个方向
//            int newx = startx + d[i][0];
//            int newy = starty + d[i][1];
//            if (isArea(newx,newy) && !visited[newx][newy] && grid[newx][newy] == '1') {
//                //下一步要在坐标内 没标记访问过 是陆地
//                dfs(grid, newx, newy);
//            }
//        }
//        return;
//    }


    public int numIslands(char[][] grid) {
        if(grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    //广度优先搜索
                    res++;
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i * n + j); //把坐标转为数字
                    visited[i][j] = true;

                    while(!queue.isEmpty()) {
                        int cur = queue.poll();
                        int curX = cur / n;
                        int cutY = cur % n;
                        //四个方位陆续入队（符合条件的）
                        for(int z = 0; z < 4; z++) {
                            int newX = curX + d[z][0];
                            int newY = cutY + d[z][1];
                            if (isArea(newX,newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                                visited[newX][newY] = true;
                                queue.add(newX * n + newY);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char grid1[][] = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println((new Solution()).numIslands(grid1));

        char grid2[][] = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println((new Solution()).numIslands(grid2));
    }
}
