## 64.最小路径和

### 题目描述
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:
```
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
```

### 思路1
递归（记忆化搜索）：要找出一条从左上角到右下角的路径，使得路径上的数字总和为最小，我们从起点开始深度优先搜索，两个方向不断搜索下去，直到到达终点即可。
在搜索的过程中，哪条路径和最短选择哪条，并且存在重叠子问题计算，采用memery数组进行记忆。
```   
    private int m;
    private int n;
    private int[][] memery;
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        memery = new int[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(memery[i], -1);
        }
        return minPathSum(grid, 0, 0);
    }

    private int minPathSum(int[][] grid, int x, int y) {
        if (x == m - 1 && y == n -1) {
            //到达了终点
            return grid[x][y];
        }
        if (memery[x][y] != -1) {
            return memery[x][y];
        }
        if (x + 1 == m || y + 1 == n) {
            //当前位置到了边界上
            if (x + 1 == m) {
                //到了最后一行,取值结果为 当前数字 + 右边的路径和
                memery[x][y] = grid[x][y] + minPathSum(grid, x, y + 1);
            }
            if (y + 1 == n) {
                //到了最后一列，取值结果为 当前数字 + 下边的路径和
                memery[x][y] = grid[x][y] + minPathSum(grid, x + 1, y);
            }
        } else {
            //看两个方向路径和 取最小值
            memery[x][y] = Integer.min(grid[x][y] + minPathSum(grid, x, y + 1),
                    grid[x][y] + minPathSum(grid, x + 1, y ));
        }
        return memery[x][y];
    }
```

### 思路2
动态规划：令 dp[i][j] 是起点左上角（0，0）到达（i, j）最小路径和, 动态转移方程为：dp[i][j] = min(dp[i-1][j] + dp[i][j-1]) + grid[i][j] (两个方向)

注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，的值都是前一个值加上当前数组元素的值。（毕竟路径和嘛 要加起来）

然后最后返回dp[m - 1][n - 1]就是我们要的结果。
```
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
```