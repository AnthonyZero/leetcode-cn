package com.anthonyzero.easy._0058;

public class Solution {

    /*public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        if (words == null || words.length == 0) {
            return 0;
        } else {
            return words[words.length - 1].length();
        }
    }*/

    public int lengthOfLastWord(String s) {
        s = s.trim();
        int index = s.lastIndexOf(" ") + 1;
        return s.substring(index).length();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "hello world ";
        System.out.println(solution.lengthOfLastWord(word));
    }
}
