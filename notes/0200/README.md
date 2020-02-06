## 200. 岛屿数量

### 题目描述
给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

示例1:
```
输入:
11110
11010
11000
00000

输出: 1

```

示例2:
```
输入:
11000
11000
00100
00011

输出: 3

```
### 思路1
深度优先搜索：从一个是 “陆地” 的格子开始进行一次 “深度优先遍历”，把与之相连的所有的格子都标记上，视为发现了一个 “岛屿”。

那么我们就线性扫描整个二维网格，如果一个结点包含 1（是陆地且还未标记过），则以其为起点启动深度优先搜索。在深度优先搜索过程中，每个访问过的结点被标记为 0。启动深度优先搜索的起点个数，即为岛屿的数量。

```   
    private boolean[][] visited;
    private int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m,n; //二维网络的大小

    public int numIslands(char[][] grid) {
        if(grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        int res = 0; //结果
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    //从陆地开始 且此陆地没被标记过
                    res++;
                    //每一次搜索 从i,j出发 就可以把相连的陆地做标记 形成一块岛屿 res++
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    
    //深度优先搜索 把每一步陆地设置为访问状态 每做一次就找到了一块岛屿（岛屿与岛屿之间没有陆地相连）
    private void dfs(char[][] grid, int startx, int starty) {

        visited[startx][starty] = true;

        for (int i = 0; i < 4; i++) {
            //4个方向
            int newx = startx + d[i][0];
            int newy = starty + d[i][1];
            if (isArea(newx,newy) && !visited[newx][newy] && grid[newx][newy] == '1') {
                //下一步要在坐标内 没标记访问过 是陆地
                dfs(grid, newx, newy);
            }
        }
        return;
    }

    //判断是否是合法坐标
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
```

### 思路2
广度优先搜索：线性扫描整个二维网格，如果一个结点是陆地且还未访问过，则以其为根结点启动广度优先搜索。将其放入队列中，并设置访问过的标志。迭代地搜索队列中的每个结点，直到队列为空。
队列为空的时候代表搜索一块岛屿结束，此时这块岛屿包含的所有陆地已经有了访问的标示。

>所有加入队列的结点，都应该马上被标记为 “已经访问”，而不是出队的时候标记，否则有可能会被重复加入队列。

```
    private boolean[][] visited;
    private int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m,n; //二维网络的大小

    //判断是否是合法坐标
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
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
```