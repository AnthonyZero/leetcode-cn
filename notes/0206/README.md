## 206. 反转链表

### 反转一个单链表。
示例:
```  
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```  

进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
### 思路1
遍历列表，将当前节点的 next 指针改为指向前一个元素。由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。在更改引用之前，还需要另一个指针来存储下一个节点。

复杂度分析

时间复杂度：O(n)，假设 n 是列表的长度，时间复杂度是 O(n)。

空间复杂度：O(1)。
```   
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
```

### 思路2
递归方式：假设列表的其余部分比如1—>2<—3<—4<—5 (其中2到5)已经被反转，现在我们该如何反转它前面的部分？
1. 直接当前节点指针发生变化 反转 比如1.next.next = 1
2. 当前节点的下一节点指针断开，避免形成环形链表 1.next = null

递归的过程：

1—>2—>3—>4—>5 //head在5这个节点上 第一次递归返回

1—>2—>3—>4<—5

1—>2—>3<—4<—5

1—>2<—3<—4<—5

1<—2<—3<—4<—5
```   
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
```   
复杂度分析

时间复杂度：O(n)，假设 nn 是列表的长度，那么时间复杂度为 O(n)。

空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 n层