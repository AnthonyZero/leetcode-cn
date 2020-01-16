package com.anthonyzero.medium._0211;

import java.util.TreeMap;

public class Solution {

    class WordDictionary {

        private Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            if (!cur.isWord) {
                cur.isWord = true;
            }
        }

        //字符串匹配搜索 （是否是单词）
        public boolean search(String word) {
            return match(root, word, 0);
        }

        private boolean match(Node node, String word, int index) {
            if (index == word.length()) {
                return node.isWord; //不能直接返回true 这不是前缀
            }
            char c = word.charAt(index); //查看当前此字段是否匹配
            if (c != '.') {
                //普通字符
                if (node.next.get(c) != null) {
                    return match(node.next.get(c), word, index + 1);
                } else {
                    return false;
                }
            } else {
                //通配符
                for(char nextChar : node.next.keySet()) {
                    //每一个分叉 只要存在一个匹配成功 就行
                    if (match(node.next.get(nextChar), word, index+1)) {
                        return true;
                    }
                }
                //一个分叉 都没匹配成功
                return false;
            }
        }

        class Node {
            private boolean isWord; //是否是单词
            private TreeMap<Character, Node> next;
            public Node() {
                isWord = false;
                next = new TreeMap<>();
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new Solution().new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }
}
