package com.anthonyzero.medium._0347;

import java.util.*;

public class Solution {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>(); //每个元素 -》 元素频率
        for(int num: nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 优先队列(默认是最小堆) 只允许存最多k个元素 （我们要的结果：这k个元素是 所有元素中频率排前的）
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) { //指定元素频率的比较
                return map.get(o1) - map.get(o2);  //频率越小 越在队头
            }
        });
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                //队列还没到指定容量 直接入队
                queue.add(entry.getKey());
            } else if (entry.getValue() > map.get(queue.peek())) {
                // 后面的元素的频率 如果大于队头元素频率  则出队队头 进行替换
                queue.remove();
                queue.add(entry.getKey());
            }
        }

        List<Integer> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            list.add(queue.remove());
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3,3,4,4,4,5,5,6,7,8,8};
        int k = 2;
        System.out.println(new Solution().topKFrequent(nums, k));
    }
}
