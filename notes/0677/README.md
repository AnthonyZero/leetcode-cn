## 677. 键值映射

### 题目描述
实现一个 MapSum 类里的两个方法，insert 和 sum。

对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。

对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。

示例:

```
输入: insert("apple", 3), 输出: Null
输入: sum("ap"), 输出: 3
输入: insert("app", 2), 输出: Null
输入: sum("ap"), 输出: 5
```


### 思路
在前缀树中插入键值对。
调用sum时，如果给定的prefix不在前缀树中，返回0。说明不存在以此字符串为前缀的单词。
否则找到前缀树中与prefix匹配完毕后的子树的根（相当于迭代完了prefix的所有字符），对所有子树中的单词求和。

```   
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
```

