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
                if (map.get(o1) != map.get(o2)) {
                    return map.get(o1) - map.get(o2);  //频率低的在队头
                } else {
                    return o2.compareTo(o1);
                }
            }
        });
        for (String word : map.keySet()) {
            queue.add(word);
            if (queue.size() > k) {  //超过了k个的话 频率低的出队
                queue.remove();
            }
        }

        List<String> topK = new ArrayList<>();
        while(!queue.isEmpty()) {
            topK.add(queue.remove());
        }
        Collections.reverse(topK);
        return topK;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(new Solution().topKFrequent(words, 2));
    }
}
