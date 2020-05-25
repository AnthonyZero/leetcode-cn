package com.anthonyzero.medium._0146;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {

    class LRUCache {
        private int capacity;
        //最近最少使用在头部 插入顺序
        private LinkedHashMap<Integer,Integer> cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new LinkedHashMap<>(capacity);
        }

        public int get(int key) {
            if(cache.containsKey(key)) {
                int value = cache.get(key);
                cache.remove(key); //删除之后 再插入排在末尾
                cache.put(key, value);
                return value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(cache.containsKey(key)) {
                cache.remove(key); //元素已存在 同理get 需要调整顺序 先remove再put
                cache.put(key, value);
            } else {
                if(cache.size() >= capacity) {
                    //容量达到上限 先删除头部元素（最近最少使用的元素） 再插入
                    Map.Entry<Integer, Integer> head = cache.entrySet().iterator().next();
                    cache.remove(head.getKey());
                }
                cache.put(key, value);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new Solution().new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);                        // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);                        // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}
