package com.anthonyzero.easy._0387;

public class Solution {

    public int firstUniqChar(String s) {
        if(s == null || "".equals(s)) {
            return -1;
        }
        int[] freq = new int[26]; //每个字母 的频率 0是代表a 依次类推
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq[c - 'a'] = freq[c - 'a'] + 1;
        }
        for (int i = 0; i < s.length(); i++) {
            //挨个看每个字母 的频率是否为1（不重复 第一个就返回了）
            if(freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().firstUniqChar("loveleetcode"));
    }
}
