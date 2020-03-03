## 147. 对链表进行插入排序

### 题目描述
对链表进行插入排序。

![插入排序](https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif)

插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

插入排序算法：
1. 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
2. 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
3. 重复直到所有输入数据插入完为止。

示例1:
```
输入: 4->2->1->3
输出: 1->2->3->4
```

示例2:
```
输入: -1->5->3->4->0
输出: -1->0->3->4->5
```

### 思路
此题是单链表，给待插入元素找插入点的时候 只能每次在已排序数组中从链表头开始找插入位置。
```   
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0); //虚拟头节点
        dummyHead.next = head;

        ListNode pre = head; //待插入节点的前一个节点
        ListNode cur = head.next; //待插入节点 从第二个元素开始
        while(cur != null) {
            //待插入元素的比前面（已排好顺序）的最大节点值要小,说明要寻找插入位置，可以插入到前面去
            if (cur.val < pre.val) {
                ListNode node = dummyHead;
                //通过这次循环 找到待插入位置，cur插入到node 和node.next的中间
                while(node.next != null && cur.val > node.next.val) {
                    node = node.next;
                }
                pre.next = cur.next; //断开当前待插入元素cur
                cur.next = node.next; //cur链接node.next
                node.next = cur; //node.next链接cur 插入完毕
                cur = pre.next; //继续看下个元素
            } else {
                //比最大元素还要大，说明当前元素不需要移动，继续看下个元素
                pre = pre.next;
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
```