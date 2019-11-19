package com.anthonyzero.hard._0003;

import java.util.HashMap;
import java.util.Map;

public class Solution {

   /* public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int maxLength = 1;
        int i = 1;
        Map<Character,Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        while(i < s.length()) {
            if (map.keySet().contains(s.charAt(i))) {
                //已存在
                if (map.size() > maxLength) { //最长子串 有新值
                    maxLength = map.size();
                }
                // 拿到上一次出现这个字符的位置 从下一个字符开始
                i = map.get(s.charAt(i)) + 1;
                map.clear();
                map.put(s.charAt(i), i); //重新计数
            } else {
                map.put(s.charAt(i), i);
            }
            i++;
        }
        return Integer.max(maxLength, map.size());
    }*/


    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.keySet().contains(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1); //移动left指针
            }
            map.put(s.charAt(i), i); //保存字符的位置
            maxLength = Math.max(maxLength, i - left + 1); //每次移动都更新当前子串最大长度 两个指针之间的距离（字串长度大小）
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
