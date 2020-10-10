## 474.一和零

### 题目描述
在计算机界中，我们总是追求用有限的资源获取最大的收益。

现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。

你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。


示例 1:
```
输入: strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
输出: 4
解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
```

示例 2:
```
输入: strs = ["10", "0", "1"], m = 1, n = 1
输出: 2
解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
```


### 思路
跟01背包类似，每个物品分类讨论的标准是：选与不选

和正常背包问题相比只是多了一个维度，这里我用三维数组理解起来简单些。后面可以通过优化使用二维空间

dp[index][i][j]表示在strs中从只考虑0到index的字符串，并且使用i个0和j个1能得到的最优解。

状态转移方程为：dp[index][i][j] = max(dp[index-1][i][j], dp[index-1][i-?][j-?]+1) （可选择的前提下）

为了避免index-1不越界，通常多设置一行。这里可以认为，第0个字符串是空串。第0行默认初始化为0。 循环的时候从1开始（但是是第0个索引对应的字符串）

输出是最后一个状态，即：dp[len][m][n]。
```   
    public int findMaxForm(String[] strs, int m, int n) {
        int dp[][][] = new int[strs.length + 1][m + 1][n + 1];
        //第0行 没有字符串默认dp值都是0
        for(int index = 1; index <= strs.length; index ++) {
            //从1开始 防止index-1越界
            Map<Integer,Integer> map = countZeroOne(strs[index - 1]);
            for(int i = 0; i <= m; i++) {
                for(int j = 0; j <= n; j++) {
                    dp[index][i][j] = dp[index - 1][i][j];

                    if(i >= map.get(0) && j >= map.get(1)) {
                        //满足分配条件
                        dp[index][i][j] = Integer.max(dp[index - 1][i][j],
                                    dp[index - 1][i - map.get(0)][j - map.get(1)] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private Map<Integer,Integer> countZeroOne(String str) {
        int zeroNum = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '0') {
                zeroNum++;
            }
        }
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, zeroNum);
        map.put(1, str.length() - zeroNum);
        return map;
    }

``` 

