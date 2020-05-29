package com.anthonyzero.easy._0392;

public class Solution {

    public boolean isSubsequence(String s, String t) {
        if ("".equals(s)) {
            return true;
        }
        int i = 0;
        int j = 0;
        while(j < t.length()) {
            if (i == s.length()) {
                return true;
            }
            if(s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        if (i == s.length()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isSubsequence("abc", "abffccgdc"));
    }
}
