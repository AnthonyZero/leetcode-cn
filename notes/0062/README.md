## 62.不同路径

### 题目描述
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

![图](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png)
例如，上图是一个7 x 3 的网格。有多少可能的路径？

示例 1:
```
输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
```

示例 2:
```
输入: m = 7, n = 3
输出: 28
```

### 思路1
递归（记忆化搜索）：我们想如果要到达终点，那么是终点左边的网格移动过来，要么是终点上面的网格移动下来。（右移和下移肯定是不同的路径）除此之外无其他走法。
此时把大问题转成小问题求解了，求总共有多少条不同的路径就是求 两个方向的路径之和。
```   
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
```

### 思路2
动态规划：令 dp[i][j] 是到达 i, j 最多不同的路径, 动态转移方程为：dp[i][j] = dp[i-1][j] + dp[i][j-1]

注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1

由于dp值依赖左和上的结果, 所以可只需要两行数据，这样就优化了空间为O(2n)
```
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
```