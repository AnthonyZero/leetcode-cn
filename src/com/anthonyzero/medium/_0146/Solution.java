package com.anthonyzero.medium._0146;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {

//    class LRUCache {
//        private int capacity;
//        //最近最少使用在头部 插入顺序
//        private LinkedHashMap<Integer,Integer> cache;
//
//        public LRUCache(int capacity) {
//            this.capacity = capacity;
//            this.cache = new LinkedHashMap<>(capacity);
//        }
//
//        public int get(int key) {
//            if(cache.containsKey(key)) {
//                int value = cache.get(key);
//                cache.remove(key); //删除之后 再插入排在末尾
//                cache.put(key, value);
//                return value;
//            }
//            return -1;
//        }
//
//        public void put(int key, int value) {
//            if(cache.containsKey(key)) {
//                cache.remove(key); //元素已存在 同理get 需要调整顺序 先remove再put
//                cache.put(key, value);
//            } else {
//                if(cache.size() >= capacity) {
//                    //容量达到上限 先删除头部元素（最近最少使用的元素） 再插入
//                    Map.Entry<Integer, Integer> head = cache.entrySet().iterator().next();
//                    cache.remove(head.getKey());
//                }
//                cache.put(key, value);
//            }
//        }
//    }

    class LRUCache {
        private int capacity; //荣量
        private HashMap<Integer,ListNode> map; //缓存 key -> listNode节点

        private ListNode dummyHead; //虚拟头节点
        private ListNode dummyTail; //虚拟尾节点

        //双向链表的节点
        class ListNode{
            int key;
            int value;
            ListNode pre; //前驱
            ListNode next; //后继
            ListNode(){}
            ListNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            dummyHead = new ListNode();
            dummyTail = new ListNode();
            dummyHead.next = dummyTail;
            dummyTail.pre = dummyHead;
        }

        public int get(int key) {
            if(!map.containsKey(key)) {
                return -1;
            }
            ListNode node = map.get(key);
            //如果 key 存在，先通过哈希表定位到链表的位置，再移到头部
            removeNode(node);
            addNodeToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            if(!map.containsKey(key)) {
                //不存在 创建一个新的节点
                ListNode newNode = new ListNode(key, value);
                // 添加至双向链表的头部
                addNodeToHead(newNode);
                // 添加进哈希表
                map.put(key, newNode);
                if(map.size() > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点  同时删除哈希表中对应的项
                    ListNode delNode = dummyTail.pre;
                    removeNode(delNode);
                    map.remove(delNode.key);
                }
            } else {
                //key存在 先通过哈希表定位，再修改 value，并移到头部
                ListNode node = map.get(key);
                node.value = value;
                removeNode(node);
                addNodeToHead(node);
            }
        }

        // 删除一个节点（前后指针断开）
        private void removeNode(ListNode node) {
            ListNode pre = node.pre;
            ListNode next = node.next;
            pre.next = next;
            next.pre = pre;
            node.pre = null;
            node.next = null;
        }

        // 把元素 移动到链表头部
        private void addNodeToHead(ListNode node) {
            ListNode nextNode = dummyHead.next; //当前头节点下个节点  要插入到头和此节点之间
            dummyHead.next = node;
            nextNode.pre = node;
            node.pre = dummyHead;
            node.next = nextNode;
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
