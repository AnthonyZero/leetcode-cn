package com.anthonyzero.medium._0676;

import java.util.*;

public class Solution {

    /**
     * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
     * [[], [["hello","hallo","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
     *
     * 结果 [null,null,true,true,false,false]  hello虽然全匹配hello理应返回false 但是可以通过替换一次机会匹配hallo 返回true 这点需要注意
     */
    /*class MagicDictionary {
        private Node root;
        public MagicDictionary() {
            root = new Node();
        }

        public void buildDict(String[] dict) {
            for (String word : dict) {
                Node cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (cur.next[c - 'a'] == null) {
                        cur.next[c - 'a'] = new Node();
                    }
                    cur = cur.next[c - 'a'];
                }
                cur.isWord = true;
            }
        }


        public boolean search(String word) {
            return search(root, word, 0, 1);
        }

        private boolean search(Node node, String word, int index, int num) {
            if (num < 0) { //替换次数已经超过了1次
                return false;
            }
            if (index == word.length()) { //所有字符匹配结束 要求只能使用一次
                return num == 0 && node.isWord;
            }
            char c = word.charAt(index);
            for (int j = 0; j < 26; j++) {
                // 只要存在一个分支 就行
                if (node.next[j] != null) {
                    if (c - 'a' == j) {
                        if (search(node.next[j], word, index + 1, num)) {
                            return true;
                        }
                    } else if (search(node.next[j], word, index + 1, num - 1)) { //使用一次替换机会
                        return true;
                    }
                }
            }
            return false;
        }



        class Node {
            private boolean isWord;
            private Node[] next;
            public Node() {
                this.isWord = false;
                next = new Node[26];
            }
        }
    }*/


    class MagicDictionary {
        private Map<Integer, List<String>> map;
        public MagicDictionary() {
            map = new HashMap<>();
        }

        public void buildDict(String[] dict) {
            for (String word : dict) {
                int length = word.length();
                if (map.containsKey(length)) {
                    List<String> words = map.get(length);
                    words.add(word);
                    map.put(length, words);
                } else {
                    map.put(length, new ArrayList<>(Arrays.asList(word)));
                }
            }
        }


        public boolean search(String word) {
            if (word == null || word.length() == 0) {
                return false;
            }
            List<String> list = map.get(word.length());
            if (list == null || list.size() == 0) {
                return false;
            }
            for (String pattern : list) {
                int count = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) != pattern.charAt(i)) {
                        count++;
                    }
                }
                if(count == 1) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new Solution().new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello","hallo","leetcode"});
        System.out.println(magicDictionary.search("hello"));
        System.out.println(magicDictionary.search("hhllo"));
        System.out.println(magicDictionary.search("hell"));
        System.out.println(magicDictionary.search("leetcoded"));
    }
}
