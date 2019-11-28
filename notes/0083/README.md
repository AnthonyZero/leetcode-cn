## 83.删除排序链表中的重复元素

### 题目描述
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例1:
```
输入: 1->1->2
输出: 1->2
```

示例2:
```
输入: 1->1->2->3->3
输出: 1->2->3
```

### 思路
由于输入的列表已排序，因此我们可以通过将结点的值与它之后的结点进行比较来确定它是否为重复结点。如果它是重复的，我们更改当前结点的 next 指针，以便它跳过下一个结点并直接指向下一个结点之后的结点

复杂度分析

时间复杂度：O(n)，因为列表中的每个结点都检查一次以确定它是否重复，所以总运行时间为 O(n)，其中 n 是列表中的结点数。

空间复杂度：O(1)，没有使用额外的空间。

```   
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        while(pre.next != null) {
            if (pre.val == pre.next.val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return head;
    }
```