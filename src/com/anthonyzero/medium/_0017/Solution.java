package com.anthonyzero.medium._0017;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private String letterMap[] = {
                "abc",  //2
                "def",  //3
                "ghi",  //4
                "jkl",  //5
                "mno",  //6
                "pqrs", //7
                "tuv",  //8
                "wxyz"  //9
    };
    private List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) {
            return new ArrayList<>();
        }
        findLetterPath(digits, 0, "");
        return res;
    }

    // 按照每个数字递归下去 寻找每一步的路径。 prefix是digits[index-1] 的结果
    private void findLetterPath(String digits, int index, String prefix) {
        if (index == digits.length()) {
            //所有数字已经处理完
            res.add(prefix);
            return;
        }
        char c = digits.charAt(index); //数字 2~9
        String letter = letterMap[c - '2'];
        for(int i = 0; i < letter.length(); i++) {
            findLetterPath(digits, index + 1, prefix + letter.charAt(i));
        }
        return;
    }

    public static void main(String[] args) {
        List<String> res = new Solution().letterCombinations("23");
        System.out.println(res);
    }
}
