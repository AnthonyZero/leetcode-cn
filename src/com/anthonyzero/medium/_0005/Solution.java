package com.anthonyzero.medium._0005;

public class Solution {

    public String longestPalindrome(String s) {
        if(s == null || "".equals(s) || s.length() == 1) {
            return s;
        }

        boolean dp[][] = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = true; //一个字符也算是回文
        }

        int maxLength = 1; //最大回文长度
        int start = 0; //ac 两个字符情况下 至少取一个字符
        for(int j = 1; j < s.length(); j++) {
            for(int i = 0; i < j; i++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2) {
                        //aba情况 直接为true
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                } else {
                    dp[i][j] = false;
                }

                //有新的回文子串
                if(dp[i][j] && maxLength < j - i + 1) {
                    //更新最长回文子串
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }


    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("aaaa"));
    }
}
