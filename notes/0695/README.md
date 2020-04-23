## 695. 岛屿的最大面积

### 题目描述
给定一个包含了一些 0 和 1 的非空二维数组 grid 。

一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)

示例1:
```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
```

示例2:
```
[[0,0,0,0,0,0,0,0]]

对于上面这个给定的矩阵, 返回 0。
```

注意: 给定的矩阵grid 的长度和宽度都不超过 50。

### 思路
深度优先搜索：通过深度优先搜索知道网格中每个连通形状的面积，每次比较更新最大值即可。

在深度优先搜索的过程，以 4 个方向探索与之相连的每一个土地，一是要满足陆地条件二是不能重复访问，在搜索的过程中要把访问过的陆地做个标记确保每个土地访问不超过一次。
```   
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
```
