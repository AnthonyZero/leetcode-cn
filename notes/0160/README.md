## 160.相交链表

### 题目描述
编写一个程序，找到两个单链表相交的起始节点。

示例1:
![示例1](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)
```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

示例2:
![示例2](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_2.png)
```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```


示例3:
![示例3](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png)
```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```
### 思路1
遍历两个链表，两个指针每走一次就判断哈希表中是否存在此节点，存在就代表相遇点返回。直到两个指针都走完路程。
```   
     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while(headA != null || headB != null) {
            if (headA != null) {
                if(set.contains(headA)) {
                    return headA;
                }
                set.add(headA);
                headA = headA.next;
            }
            if (headB != null) {
                if(set.contains(headB)) {
                    return headB;
                }
                set.add(headB);
                headB = headB.next;
            }
        }
        return null;
    }
```

### 思路2
双指针法：创建两个指针 pA和 pB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
当 pA 到达链表的尾部时，将它重定位到链表 B 的头结点 ; 类似的，当 pB 到达链表的尾部时，将它重定位到链表 A 的头结点。（消除了两个链表的长度差）

如果他们有相交点，在某一时刻 pA 和 pB 肯定相遇（相遇点为结果）。如果不相交，他们最后都走到了对方链表的末尾, 相当于走了相同的路途（走的最大路径为：链表A+链表B的长度）。
```
   public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
       if(headA == null || headB == null) {
           return null;
       }
       ListNode nodeA = headA, nodeB = headB;
       while(nodeA != nodeB) {
           //如果有相交点 肯定会相遇的 他们走的路程是一样的
           nodeA = (nodeA == null ) ?  headB : nodeA.next;  //nodeA 到了末尾 指针指向headB 开始往后走
           nodeB = (nodeB == null) ? headA : nodeB.next;   //nodeB 到了末尾 指针指向headA 开始往后走
       }
       //while 退出的时候 nodeA = nodeB 有两种情况 一：相遇点 nodeA nodeB 同一个节点不等于null  二：都走完了，最后null == null 退出
       //如果相遇 nodeA
       return nodeA;
   }
```