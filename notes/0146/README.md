## 146.LRU缓存机制

### 题目描述
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

进阶:
* 你是否可以在 O(1) 时间复杂度内完成这两种操作？

示例:
```
LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4

```

### 思路1 
使用JDK自带的LinkedHashMap实现（利用插入顺序）
```   
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
```

### 思路2：哈希表 + 双向链表
LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
* 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
* 哈希表即为普通的哈希映射（HashMap），通过缓存数据的键key映射到其在双向链表中的位置。

关键两个方法：
1. 从双向链表中删除指定节点的removeNode方法
2. 将一个节点插入到双向链表开头的addNodeToHead方法

get的时候，先通过哈希表获取到节点，执行remove然后执行addHead，相当于把节点移到了双向链表的开头。
put的时候，如果键存在，先覆盖node的值，同理get操作，把节点移到了双向链表的开头。如果键不存在，哈希表put添加新节点，同时链表把此节点加到头部。继续判断此时如果容量已满，就删除最后一个节点（最近最少使用）
```  
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
```  