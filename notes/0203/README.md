## 203. 移除链表元素

### 题目描述
删除链表中等于给定值 val 的所有节点。

示例:
```
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
```

### 思路1
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

### 思路2
采用递归的思路。考虑问题规模最小的情况（head为空递归退出条件）。减小问题规模，获得处理后的链表。判断当前头节点是否需要删除，如果需要头节点移动到递归处理后的链表头，如果目标值不相等代表不删除，则头节点关联上已经处理的链表头。
```       
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode listNode = removeElements(head.next, val); //后面已经完成了目标 删除了目标值的 链表（返回的头节点）
        if (head.val == val) {
            head = listNode;  //代表删除当前头
        } else {
            head.next = listNode; //不删除 关联起来
        }
        return head;
    }
```   
