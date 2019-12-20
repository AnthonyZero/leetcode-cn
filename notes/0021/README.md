## 21.合并两个有序链表

### 题目描述
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

```
示例 1:

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

```

### 思路
因为两个合并的链表都是有序：所以创建一个新结点指针，迭代挑选l1和l2中的小者入链尾即可。
当l1和l2其中一个到达链表后跳出循环，直接把当前新链表链尾指向还没遍历完的链表当前节点,完成合并。

```   
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode pre = dummyNode;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = new ListNode(l1.val);
                l1 = l1.next;
                pre = pre.next;
            } else if (l1.val > l2.val) {
                pre.next = new ListNode(l2.val);
                l2 = l2.next;
                pre = pre.next;
            } else {
                pre.next = new ListNode(l1.val);
                pre.next.next = new ListNode(l1.val);
                pre = pre.next.next;
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            pre.next = l2;
        } else {
            pre.next = l1;
        }
        return dummyNode.next;
    }
```

