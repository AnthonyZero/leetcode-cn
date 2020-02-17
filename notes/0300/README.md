## 300.最长上升子序列

### 题目描述
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例 :
```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```
说明
* 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
* 你算法的时间复杂度应该为 O(n2) 。

### 思路
动态规划：dp[i] 所有元素置为1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1。
从i = 1开始，按个看当前i前面的数字是否可以和当前i这个元素组成上升子序列，即转移方程为：dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)

返回 dp列表最大值，即可得到全局最长上升子序列长度。
```   
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length]; //dp[i] = max(dp[i], dp[j] + 1) nums[i] > nums[j]
        for(int i = 0; i < dp.length; i++) {
            dp[i] = 1; //初始化长度为1
        }
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Integer.max(dp[i], dp[j] + 1);
                }
            }
            result = Integer.max(result, dp[i]);
        }
        //return dp[nums.length - 1]; //错误返回
        return result;
    }
```

复杂度分析：
* 时间复杂度 O(N^2) ：遍历计算 dp 列表需 O(N)，计算每个 dp[i]需 O(N)。
* 空间复杂度 O(N) ： dp列表占用线性大小额外空间。


