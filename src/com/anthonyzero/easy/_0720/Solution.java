package com.anthonyzero.easy._0720;

import java.util.Set;
import java.util.TreeMap;

public class Solution {

    public String res = ""; //最大长度的单词（在前缀树中含有所有前缀）
    public String longestWord(String[] words) {
        //构建前缀树
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        //深度优先搜索
        dfs(trie.root, new StringBuilder());
        return res;
    }

    // sb在深度搜索的过程中 不断在变化，搜索完毕 res(最大长度单词)也就构造完成
    public void dfs(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        } else if (res.length() < sb.length()) {
            res = sb.toString(); //更新最大值（单词）
        } else if (res.length() == sb.length()) {
            if(res.compareTo(sb.toString()) > 0) {
                res = sb.toString(); //长度相同的情况 取字典序在前面的单词
            }
        }
        //深度搜索下去
        Set<Character> characters = node.next.keySet();
        for(Character c : characters) {
            //当前节点是个单词 才符合条件
            if (node.next.get(c).isWord) {
                //搜索到这 添加此字符 继续dfs（比较res长度）
                sb.append(c);
                dfs(node.next.get(c), sb);
                //这很重要 回溯（要删掉上一步选择的路径 也就是上一步添加的字符）
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    class Node {
        private boolean isWord; //代表是个单词
        private TreeMap<Character,Node> next;
        public Node() {
            isWord = false;
            next = new TreeMap<>();
        }
    }

    class Trie {
        private Node root;
        public Trie() {
            root = new Node();
        }
        //添加一个单词
        public void insert(String word) {
            Node cur = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!cur.next.containsKey(c)) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            cur.isWord = true;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println(new Solution().longestWord(words));
    }
}
