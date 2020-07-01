package com.anthonyzero.medium._0718;

import java.util.Arrays;

public class Solution {

    public int findLength(int[] A, int[] B) {
        //dp[i][j]代表以A[i-1]与B[j-1]结尾的公共字串的长度,公共字串必须以A[i-1]，B[j-1]结束
        //即当A[i-1] == B[j-1]时，dp[i][j] = dp[i-1][j-1] + 1;
        // 当A[i-1] != B[j-1]时，以A[i-1]和B[j-1]结尾的公共字串长度为0,dp[i][j] = 0。（此时包含了A[i-1] B[j-1] 因为子串必须连续 不相等直接为0）
        int[][] dp = new int[A.length + 1][B.length + 1]; //第一行 第一列不用 都是0
        //结果maxLength的值 就是找dp数组中最大的值 返回
        int maxLength = 0;
        //递推
        for(int i = 1; i <= A.length; i++) {
            for(int j = 1; j <= B.length; j++) {
                if(A[i - 1] != B[j - 1]) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1; //
                }
                maxLength = Integer.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] A = new int[]{0,0,0,0,1};
        int[] B = new int[]{1,0,0,0,0};
        System.out.println(new Solution().findLength(A, B));
    }
}
