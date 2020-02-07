## 51. N皇后

### 题目描述
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
![n皇后](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/8-queens.png)

上图为 8 皇后问题的一种解法。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

示例 :
```
输入: 4
输出: [
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法。
```

### 思路
首先要知道的是一行只可能有一个皇后且一列也只可能有一个皇后，且每一行都要放置一个皇后。

从第一个 row = 0 开始，循环列并且试图在每个 column 中放置皇后.
* 如果方格 (row, column) 不在占用范围内
* 在 (row, column) 方格上放置皇后。
* 排除对应此列和两个对角线的位置。
* IF如果所有的行都已被放置了Queue，意味着我们找到了一个解,添加到我们的结果集中
* Else 继续考虑接下来的皇后放置 putQueues(row + 1).
* 回溯：将在 (row, column) 方格的皇后移除.

```   
    private boolean cols[]; //每列占用情况
    private boolean leftLower[]; //左下->右上对角线占用情况 有2*n-1条对角线
    private boolean leftUpper[]; //左上—>右下对角线占用情况 有2*n-1条对角线
    //对于一个n*n的坐标系上  左下->右上对角线中 在同一条上对角线的坐标x,y 满足x+y 都是同样值 且是leftLower数组的下标
    //对于一个n*n的坐标系上  左上—>右下对角线中 在同一条上对角线的坐标x,y 满足x-y+n-1 都是同样值 且是leftUpper数组的下标

    private List<List<String>> res = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        cols = new boolean[n];
        leftLower = new boolean[2*n+1];
        leftUpper = new boolean[2*n+1];
        //从第1,2.. n 行开始放皇后旗子
        putQueues(n, 0, new LinkedList<>()); //每一行都要有一个皇后Q
        return res;
    }

    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置, rows表示每一行中具体哪一列摆放
    private void putQueues(int n, int index, LinkedList<Integer> rows) {
        if (index == n) {
            //表示每一行都已经有了一个位置，已找到问题的一个解
            List<String> list = new ArrayList<>();
            for(int i = 0; i < rows.size(); i++) {
                char[] charArray = new char[n];
                Arrays.fill(charArray, '.'); //每一行初始化为.
                charArray[rows.get(i)] = 'Q'; //那一列放Q
                list.add(new String(charArray));
            }
            res.add(list);
            return;
        }

        //考虑在index此行中，从第0列到n-1列放置Q, i表示哪一列
        for(int i = 0; i < n; i++) { //每列都试 循环完了才递归返回

            // 3条线 都没占用才继续搜索下去
            if (!cols[i] && !leftLower[index + i] && !leftUpper[index - i + n - 1]) {
                rows.addLast(i);
                cols[i] = true; //此列占用
                //此时的坐标x = index, y = i;  两个对角线占用
                leftLower[index + i] = true;
                leftUpper[index - i + n - 1] = true;
                //继续下一行放置Q
                putQueues(n, index + 1, rows);
                //递归返回时 回溯 恢复状态
                rows.removeLast();
                cols[i] = false;
                leftLower[index + i] = false;
                leftUpper[index - i + n - 1] = false;
            }
        }
        return;
    }
```

