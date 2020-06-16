## 120. 三角形最小路径和

### 题目描述
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

例如，给定三角形：
```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

```
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：

如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

### 思路1
递归（备忘录方式）,自顶向下
```   
    private List<List<Integer>> list;
    private int[][] memory;
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        list = triangle;
        memory = new int[list.size()][list.get(list.size() - 1).size()];
        for(int i = 0; i < memory.length; i++) {
            Arrays.fill(memory[i], -1);
        }
        return minimumTotal(0, 0);
    }

    //从index行pos列开始 到三角形底部的 最小路径和
    private int minimumTotal(int index, int pos) {
        if (index == list.size() - 1) {
            //底部 只有一行 返回所在位置值
            return list.get(index).get(pos);
        }
        if(memory[index][pos] != -1) {
            return memory[index][pos];
        }
        //两个方向 取较小值
        memory[index][pos] = Integer.min(minimumTotal(index + 1, pos),
                    minimumTotal(index + 1, pos + 1)) + list.get(index).get(pos);
        return memory[index][pos];
    }
```

### 思路2
动态规划：dp自底向上递推（空间优化只使用O(n)的一维数组）
```
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int size = triangle.size();
        int[] dp = new int[triangle.get(size - 1).size()]; //一维数组，长度取决于三角形最后一行的长度（列宽）
        for(int i = 0; i < dp.length; i++) {
            //初始化 从底部开始往三角形顶部开始走。dp初始值为底部最后一行的值
            dp[i] = triangle.get(size - 1).get(i);
        }
        //往上走，更新dp数组的值，递推的每一行只依赖于前一行的值 这里O(n)空间即可解决
        for(int i = size - 2; i >= 0; i--) { //从倒数第二行开始 往上 dp数组的每一项值 开始累加
            for(int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Integer.min(dp[j], dp[j + 1]); //此行的当前坐标值 + 下面一行的两个中的较小值
            }
        }
        return dp[0];
    }
```

