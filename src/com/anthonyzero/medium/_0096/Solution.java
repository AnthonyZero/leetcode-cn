package com.anthonyzero.medium._0096;

public class Solution {

    /**
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     * 举例如果n=3那么 dp[3] = f(1) + f(2) + f(3)  f(i)函数代表以i为根的的二叉搜索树的个数
     * 当i为根节点时，其左子树节点个数为i-1个，右子树节点为n-i，即f(i) = dp(i-1)*dp(n-i)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if(n <= 1) {
            return n;
        }
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1; //一个节点
        //从2开始求解
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numTrees(3));
    }
}
