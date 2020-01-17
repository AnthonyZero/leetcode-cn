package com.anthonyzero.medium._0677;

import java.util.TreeMap;

public class Solution {

    class MapSum {
        private Node root;
        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (!cur.next.containsKey(c)) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            cur.value = val;
        }

        public int sum(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (!cur.next.containsKey(c)) {
                    return 0;
                }
                cur = cur.next.get(c);
            }
            //已经到前缀末尾了 cur现在是以prefix为前缀的所有字符串的头部
            return getNodeSum(cur);
        }

        private int getNodeSum(Node node) {
            int result = node.value; //可能为0 也可能有值(代表是个单词)
            for (char c : node.next.keySet()) {
                result += getNodeSum(node.next.get(c));
            }
            return result;
        }
    }

    class Node {
        public int value; //键代表的值
        public TreeMap<Character, Node> next;
        public Node() {
            this.value = 0;
            next = new TreeMap<>();
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new Solution().new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }
}
