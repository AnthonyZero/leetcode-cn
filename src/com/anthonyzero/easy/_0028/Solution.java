package com.anthonyzero.easy._0028;

import java.util.Arrays;

public class Solution {


    /*public int strStr(String haystack, String needle) {
        if (needle == "") {
            return 0;
        }
        int i = 0;
        int j = 0;
        char[] main = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        while (i < main.length && j < pattern.length) {
            if (main[i] == pattern[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1 ; //i回退 从下一个开始比较
                j = 0; //归0
            }
        }
        if (j == pattern.length) {
            //while完之后 如果j等于模式串长度 说明找到了
            return i - j;
        } else {
            return -1;
        }
    }*/

    public int strStr(String haystack, String needle) {
        if (needle == "") {
            return 0;
        }
        char[] main = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        int next[] = getNext(genPrefix(pattern)); //获取next数组
        int i = 0; //主串指针位置 不回退
        int j = 0; //模式串指针位置 不匹配的时候根据next数组 回退到响应位置
        while (i < main.length && j < pattern.length) {
            if (j == -1 || main[i] == pattern[j]) {
                //如果j根据next数组 回到next[0] 前面也就是-1 那么重置j = 0 i后移一位 继续比较
                j++;
                i++;
                //第二种 如果匹配 两个指针继续后移 比较
            } else {
                //不匹配 j根据得到的next数组 回退到指定位置
                j = next[j];
            }
        }
        if (j == pattern.length) {
            //while完之后 如果j等于模式串长度 说明找到了
            return i - j;
        } else {
            return -1;
        }
    }

    //获取模式串的前缀数组
    public int[] genPrefix(char[] pattern) {
        if (pattern.length == 0) {
            return new int[0];
        }
        int[] prefix = new int[pattern.length]; //结果 最大前缀数的数组
        int len = 0;
        int i = 1; //从1开始 求字符串的最大前后缀长度 并用prefix数组记录
        prefix[0] = 0; //毫无疑问 第一个p[0] = 0
        while(i < pattern.length) {
            if (pattern[len] == pattern[i]) {
                prefix[i] = len + 1; //后缀相等
                len++;
                i++;
            } else {
                if (len == 0) {
                    //不能再后退了 也就是当前i所在字符 不等于第一个字符
                    prefix[i] = len;
                    i++;
                } else {
                    // len回退到上一级最大前缀数 然后来继续和p[i]比较
                    len = prefix[len - 1];
                }
            }
        }
        return prefix;
    }

    //为了便于方便求解 把前缀数组右移一位 第一位补-1 得到next数组
    public int[] getNext(int[] prefix) {
        if (prefix.length == 0) {
            return new int[0];
        }
        int[] next = new int[prefix.length];
        next[0] = -1;
        for (int i = prefix.length-1; i > 0; i--) {
            next[i] = prefix[i - 1];
        }
        return next;
    }


    public static void main(String[] args) {
        String pattern = "ABABCABAAC";
        Solution solution = new Solution();
        int[] prefix = solution.genPrefix(pattern.toCharArray());
        int[] next = solution.getNext(prefix);
        System.out.println(Arrays.toString(prefix));
        System.out.println(Arrays.toString(next));

        String ts = "ABCABCDABCDABDE";
        String ps = "ABCDABD";
        System.out.println(solution.strStr(ts, ps));

        String ts1 = "CABDCABCE";
        String ps1 = "ABDCABD";
        System.out.println(solution.strStr(ts1, ps1));

        String ts2 = "mississippi";
        String ps2 = "issip";
        System.out.println(solution.strStr(ts2, ps2));

        System.out.println(ts2.indexOf(ps2));
    }
}
