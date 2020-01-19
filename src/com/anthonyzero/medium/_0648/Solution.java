package com.anthonyzero.medium._0648;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public String replaceWords(List<String> dict, String sentence) {
        Node root = new Node();
        //所有词根添加到字典树中
        for (String word : dict) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new Node();
                }
                cur = cur.next[c - 'a'];
            }
            if (!cur.isWord) {
                cur.isWord = true;
            }
        }
        String[] successors = sentence.split(" ");
        StringBuilder sb = new StringBuilder("");
        for (String successor : successors) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            //找每个继承词 在字典树中的 最短词根
            List<Character> list = new ArrayList<>(); //记录每次的字符
            Node cur = root;
            for (int i = 0; i < successor.length(); i++) {
                char c = successor.charAt(i);
                if (cur.next[c - 'a'] == null) {
                    sb.append(successor);
                    break;
                } else {
                    //存在该字符
                    list.add(c);
                    if (cur.next[c -'a'].isWord) {
                        //已经找到了词根 最短词根到此结束
                        for (char character : list) {
                            sb.append(character);
                        }
                        break;
                    } else {
                        cur = cur.next[c - 'a'];
                        //这里需要注意：继承词 迭代完了 都没找到词根
                        if (i == successor.length() - 1) {
                            sb.append(successor);
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    class Node {
        boolean isWord;
        Node[] next;
        public Node() {
            this.isWord = false;
            next = new Node[26];
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"cat","bat","rat"};
        List<String> dict = Arrays.asList(strs);
        System.out.println(new Solution().replaceWords(dict, "the cattle was rattled by the battery r"));
    }
}
