package com.anthonyzero.medium._1143;

import java.util.Arrays;

public class Solution {

//    private int[][] memery;
//    public int longestCommonSubsequence(String text1, String text2) {
//        if (text1 == null || "".equals(text1) || text2 == null || "".equals(text2)) {
//            return 0;
//        }
//        memery = new int[text1.length()][text2.length()];
//        for (int i = 0; i < text1.length(); i++) {
//            Arrays.fill(memery[i], -1);
//        }
//        return longest(text1, text2, text1.length() - 1, text2.length() - 1);
//    }
//
//    private int longest(String text1, String text2, int index1, int index2) {
//        if (index1 < 0 || index2 < 0) {
//            return 0;
//        }
//        if(memery[index1][index2] != -1) {
//            return memery[index1][index2];
//        }
//        if (text1.charAt(index1) == text2.charAt(index2)) {
//            //最后两个字符相同
//            memery[index1][index2] = longest(text1, text2, index1 - 1, index2 - 1) + 1;
//        } else {
//            memery[index1][index2] = Math.max(
//                    longest(text1, text2, index1 - 1, index2),
//                    longest(text1, text2, index1, index2 - 1));
//        }
//        return memery[index1][index2];
//    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || "".equals(text1) || text2 == null || "".equals(text2)) {
            return 0;
        }
        int[][] lcs = new int[text1.length()][text2.length()];
        //初始化第一行第一列
        //只要存在有一个字符相同，那么这个字符后面的lcs结果就是1
        int temp = 0;
        for(int i = 0; i < text1.length(); i++) {
            //text1每个字符和text2第一个字符比较，text1遇到相同的时候，这后面的lcs结果都应该是1，因为此时text2只要一个字符，lcs结果最长也只是1
            if(text1.charAt(i) == text2.charAt(0)) {
                temp = 1;
            }
            lcs[i][0] = temp;
        }

        temp = 0;
        for(int i = 0; i < text2.length(); i++) {
            if (text2.charAt(i) == text1.charAt(0)) {
                temp = 1;
            }
            lcs[0][i] = temp;
        }

        //迭代过程
        for(int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Integer.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        return lcs[text1.length() - 1][text2.length() - 1];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(new Solution().longestCommonSubsequence(text1, text2));
    }
}
