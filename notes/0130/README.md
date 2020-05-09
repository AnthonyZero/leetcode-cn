## 130.被围绕的区域

### 题目描述
给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:
```
X X X X
X O O X
X X O X
X O X X

运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X
```

解释:

被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

### 思路
深度优先搜索：对边界上每一个'O'做深度优先搜索，将与其相连的所有'O'改为'P'（边界上的O以及与它连接的O要特殊处理，这部分不是题目要求的围绕区域）。

然后遍历整个矩阵，将矩阵中所有'O'(剩下的这部分O就是被围绕的区域)改为'X',将矩阵中刚处理了的所有'P'还原为'O'即可
```   
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
```
