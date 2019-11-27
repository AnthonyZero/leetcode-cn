## 203. 移除链表元素

### 题目描述
删除链表中等于给定值 val 的所有节点。

示例:
```
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
```

### 思路
对于想要删除某个节点，就必须找到这个节点的前一个节点，把前一个节点的指针改变，即指向下下一个就可以达到删除的目的了。

但是对于头节点而已如果也是目标值的话删除有点麻烦。所以考虑引入一个虚拟节点（在头节点前面），迭代往后删除目标值，返回的是虚拟节点的下一个节点（头节点）
```   
    public ListNode removeElements(ListNode head, int val) {
        ListNode virtualNode = new ListNode(-1); //虚拟节点
        virtualNode.next = head;
        ListNode pre = virtualNode; //pre遍历
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next; //不用后移，因为可能下一个又是目标值
            } else {
                pre = pre.next;
            }
        }
        return virtualNode.next;
    }
```
