package com.anthonyzero.medium._0451;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

    public String frequencySort(String s) {
        if ("".equals(s) || s == null) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>(); //每个字符出现的频率
        for (int i = 0; i < s.length(); i++) {
            Character charAt = s.charAt(i);
            if (map.containsKey(charAt)) {
                map.put(charAt, map.get(charAt) + 1);
            } else {
                map.put(charAt, 1);
            }
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {  //频率大的在队列头
                return map.get(o2) - map.get(o1);
            }
        });
        for(Character character : map.keySet()) {
            queue.add(character);
        }

        StringBuilder sb = new StringBuilder("");
        while(!queue.isEmpty()) {
            Character character = queue.remove(); //出队
            for (int i = 0; i < map.get(character); i++) { //根据频率append
                sb.append(character);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        System.out.println(new Solution().frequencySort(s));
    }
}
