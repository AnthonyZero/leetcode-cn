package com.anthonyzero.medium._0692;

import java.util.*;

public class Solution {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        PriorityQueue<String> queue = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (map.get(o2) != map.get(o1)) {
                    return map.get(o2) - map.get(o1);
                } else {
                    return o1.compareTo(o2);
                }
            }
        });
        for (String word : map.keySet()) {
            queue.add(word);
        }

        List<String> topK = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topK.add(queue.remove());
        }
        return topK;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(new Solution().topKFrequent(words, 2));
    }
}
