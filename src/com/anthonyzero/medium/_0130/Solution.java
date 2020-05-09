package com.anthonyzero.medium._0130;


import java.util.Arrays;

public class Solution {

    private int m; //行
    private int n; //列
    private int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}}; //4个方位
    public void solve(char[][] board) {
        if(board.length == 0) {
            return;
        }
        m = board.length;
        n = board[0].length;
        //从边界的O开始 把相邻的O深度搜索下去 陆续设置为P
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && ( i == 0 || i == m - 1 || j == 0 || j == n - 1 )) {
                    dfs(board, i, j);
                }
            }
        }
        //把矩阵中的O设置为X(这部分O也就是被围绕的区域) 把原先设置的P还原回O
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'P') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    //从（x,y）出发 将相邻的O 修改为P
    private void dfs(char[][] board, int x, int y) {
        if(board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'P';
        for(int i = 0; i < direction.length; i++) {
            //4个方向深度搜索下去
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];
            if (isArea(newX, newY) && board[newX][newY] == 'O') {
                dfs(board, newX, newY);
            }
        }
    }

    //是否在区域内
    private boolean isArea(int x, int y) {
        return 0 < x && x < m && 0 < y && y < n;
    }
    
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'O','X','X','O','X'},
                {'X','O','O','X','O'},
                {'X','O','X','O','X'},
                {'O','X','O','O','O'},
                {'X','X','O','X','O'}
        };
        new Solution().solve(board);
        for(int i = 0; i < board.length; i ++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
