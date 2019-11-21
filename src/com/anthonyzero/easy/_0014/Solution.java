package com.anthonyzero.easy._0014;

public class Solution {

    //最长公共前缀 前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String targetPrefix = strs[0]; //暂时第一个字符串为最长公共前缀
        for (int i = 1; i < strs.length; i++) {
            String compare = strs[i];
            int j = 0;
            for (;j < compare.length(); j++) { //两个字符串 按个比较每个字符 遇到不相等 更新targetPrefix
                if (j >= targetPrefix.length() || targetPrefix.charAt(j) != compare.charAt(j)) {
                    break;
                }
            }
            if (j == 0) { //第一个字符都不匹配
                return "";
            }
            targetPrefix = compare.substring(0, j);
        }

        return targetPrefix;
    }


    public static void main(String[] args) {
        String[] strs = new String[]{"dg","dogdd","dogg"};
        System.out.println(new Solution().longestCommonPrefix(strs));
    }
}
