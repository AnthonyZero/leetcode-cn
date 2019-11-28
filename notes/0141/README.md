## 141. 环形链表

### 题目描述
给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。


示例1:
```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```
![链表](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

示例2:
```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点
```
![链表](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png)

示例3:
```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```
![链表](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png)

### 思路1
要判断链表是否存在环，借助HashSet,链表头每移动一步判断当前节点是否存在于集合中。如果存在说明存在环。

注意:集合中的元素不能是节点的值（因为无环的链表可能存在相等值的多个节点），而是整个节点

时间复杂度：O(n) 哈希表访问一次O(1) 最多访问n个链表元素
空间复杂度：O(n) 哈希表最多存储n个链表元素
```   
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while(head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }
```

### 思路2
使用快慢指针，相当于一个环线跑道里有两个人跑步，一个跑的快，一个跑的慢。所以说如果跑道是环形的两个人终会相遇。

代码实现就是：当头节点为空或者只有一个节点的时候肯定没有环返回false;
然后快的人在第二个位置出发，慢的人在头节点第一个位置出发。如果链表没有环，那么快的人跑在前面肯定会得出结论false(while退出条件)。如果链表有环。那么两个人终会相遇（返回true）,每次快的人移动2步，慢的人移动1步。

执行用时 :0 ms, 在所有 java 提交中击败了100.00%的用户

内存消耗 :37.3 MB, 在所有 java 提交中击败了96.95%的用户

时间复杂度：O(n) 取决于链表长度n 移动
空间复杂度：O(1) 没借助其他数据结构 只有两个指针 O(1)
```
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            } else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return false;
    }
```