package com.anthonyzero.easy._0804;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int uniqueMorseRepresentations(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        String[] table = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        for (String word : words) {
            StringBuilder res = new StringBuilder("");
            for (int i = 0; i < word.length(); i++) {
                res.append(table[word.charAt(i) - 'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        Solution solution = new Solution();
        System.out.println(solution.uniqueMorseRepresentations(words));
    }
}
