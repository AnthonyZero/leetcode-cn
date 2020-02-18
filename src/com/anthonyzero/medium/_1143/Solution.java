package com.anthonyzero.medium._1143;

import java.util.Arrays;

public class Solution {

    private int[][] memery;
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || "".equals(text1) || text2 == null || "".equals(text2)) {
            return 0;
        }
        memery = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            Arrays.fill(memery[i], -1);
        }
        return longest(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    private int longest(String text1, String text2, int index1, int index2) {
        if (index1 < 0 || index2 < 0) {
            return 0;
        }
        if(memery[index1][index2] != -1) {
            return memery[index1][index2];
        }
        if (text1.charAt(index1) == text2.charAt(index2)) {
            //最后两个字符相同
            memery[index1][index2] = longest(text1, text2, index1 - 1, index2 - 1) + 1;
        } else {
            memery[index1][index2] = Math.max(
                    longest(text1, text2, index1 - 1, index2),
                    longest(text1, text2, index1, index2 - 1));
        }
        return memery[index1][index2];
    }

    public static void main(String[] args) {
        String text1 = "bl";
        String text2 = "yby";
        System.out.println(new Solution().longestCommonSubsequence(text1, text2));
    }
}
