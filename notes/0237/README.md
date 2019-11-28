## 237.删除链表中的节点

### 题目描述
请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

现有一个链表 -- head = [4,5,1,9]，它可以表示为:
![链表](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/237_example.png)

```
示例 1:

输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
示例 2:

输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```
说明
* 链表至少包含两个节点。
* 链表中所有节点的值都是唯一的。
* 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
* 不要从你的函数中返回任何结果。

### 思路
这个题只有一个参数 是我们要待删除的节点，想办法把这个节点删除。 

这个题的关键在于要删除它的当前节点，而不知道前一个结点，而每个节点比较关键的就是val,把当前节点的val换成下一节点的val,当前节点的next换成下一节点的next，也就变相的把当前节点删除了（其实删除的是当前节点的下一个节点，这两个节点值是相同的，对应题目给定的是非末尾节点）
```   
    public void deleteNode(ListNode node) {
        node.val = node.next.val; //把下一个节点的值赋给当前节点，然后将当前节点指向下下个节点 变相删除
        node.next = node.next.next;
    }
```