package com.anthonyzero.medium._1456;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int maxVowels(String s, int k) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        Set<Character> set = new HashSet<>();
        for(char c : vowels) {
            set.add(c);
        }
        int maxLength = 0;//最大元音字母数
        int left = 0; //左指针
        int i = 0; //右指针
        int current = 0; //当前窗口的元音字母数
        while(i < s.length()) {
            if(i - left < k) {
                //最开始窗口大小 还未到规定大小k 遇到是元音字母 就直接++
                if(set.contains(s.charAt(i))) {
                    current++;
                }
                i++;
            } else {
                left++; //窗口右移一位（i已经加了1了 left同步移动一位）
                //(i - left + 1 = k) 现在两个指针之间的间隔字母数量 是等于k的
                // 维持窗口大小不变 left i不断像右移 一位
                if(set.contains(s.charAt(left - 1))) {
                    current--; //当前窗口失去了前一个字符 如果是元音字母 就该减1
                }
                if(set.contains(s.charAt(i))) {
                    current++; //当前窗口包含了新的一个字符
                }
                i++;
            }
            maxLength = Integer.max(maxLength, current);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxVowels("leetcode", 4));
    }
}
