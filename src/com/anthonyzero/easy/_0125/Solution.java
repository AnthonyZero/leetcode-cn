package com.anthonyzero.easy._0125;

public class Solution {


    public boolean isPalindrome(String s) {
        if (s.equals("")) {
            return true;
        }
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        boolean result = true;
        while (i < j) {
            if (!(Character.isAlphabetic(chars[i]) || Character.isDigit(chars[i]))) {
                i++;
                continue;
            }
            if (!(Character.isAlphabetic(chars[j]) || Character.isDigit(chars[j]))) {
                j--;
                continue;
            }
            if (Character.toUpperCase(chars[i]) != Character.toUpperCase(chars[j])) {
                result = false;
                break;
            } else {
                i++;
                j--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char c = 'D';
        System.out.println(Integer.valueOf(c));
        System.out.println(Character.isAlphabetic(c));
        System.out.println(Character.isDigit(c));
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
