## 343.整数拆分

### 题目描述
给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例1 :
```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
```

示例2 :
```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
```
说明: 你可以假设 n 不小于 2 且不大于 58。

### 思路1
递归：自顶向下（记忆化），通过求子问题的最优解，可以得到原问题的最优解

```   
    private Map<Integer,Integer> memery = new HashMap<>();
    public int integerBreak(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memery.containsKey(n)) {
            return memery.get(n);
        }
        int maxnum = 0;
        //最优子结构
        for (int i = 1; i < n; i++) { // 1到n-1
            //不分裂i * (n-i) 和分裂i * integerBreak(n - i)的情况
            maxnum = Math.max(maxnum, Math.max(i * (n-i), i * integerBreak(n - i)));
        }
        memery.put(n, maxnum);
        return maxnum;
    }
```

### 思路2
将递归转化为递推，将自顶向下的思路转换为自底向上，这也是记忆化搜索和动态规划DP之间的区别所在

令dp[i]表示整数i对应的最大乘积，那么dp[i]的值应是j * (i - j)和j * dp[i - j]的最大值，j从1到i-1
``` 
    public int integerBreak(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            //从i到n 求最大乘极
            int maxnum = 0;
            for(int j = 1; j < i; j++) {
                maxnum = Math.max(maxnum, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = maxnum;
        }
        return dp[n];
    }

``` 

