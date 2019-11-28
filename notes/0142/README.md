## 142. 环形链表 II

### 题目描述
给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

说明：不允许修改给定的链表。


示例1:
```
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```
![链表](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

示例2:
```
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。
```
![链表](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png)

示例3:
```
输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。
```
![链表](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png)

进阶：
你是否可以不用额外空间解决此题？

### 思路
还是使用快慢指针slow,fast。一开始都指向head。然后slow每次走一步，fast每次走两步。走着走着,如果fast变为空，说明链表无环。

当slow==fast时，说明有环存在。因为只有环时，slow才能赶上fast。否则slow永远在fast的后面。

此时head和fast每次一步，同时向前走，直到head和fast再次相等。相等的位置就为环的入口。

为什么head到环的入口距离等于环内相遇点到环入口的距离？

设：
* x = head到入口点的记录
* y = 入口点到相遇点的距离（相遇点肯定在环内）
* b = 相遇点到入口点的距离
* n = 环长度;

那么两个相遇时：
* slow走的距离为 x+y+k1n(k1是slow在环内走的圈数)
* fast走的距离为 x+y+k2n(k2是fast在环内走的圈数)

因为fast的速度是slow的2倍，所以他们走的距离的关系是：x+y+k2n=2*（x+y+k1n）

移项：x = (k2 - 2k1)n - y， 因为y = n - b代入左边式子

最后得出 x = (k2 - 2k1 - 1)n + b

所以当fast在相遇点，head和fast同时以1步的速度前进时，fast会转(k2-2k1-1) 圈整环，再走 b 步后与head一定终会相遇在环的入口  (k2-2k1-1) >= 0

时间复杂度：O(n)

空间复杂度：O(1)
```   
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; //没有环返回null
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break; //停留在相遇点 环内 slow和fast
            }
        }
        if(slow != fast) { //没相遇返回null 代表没环
            return null;
        }

        //有环 寻找环的入环第一个节点
        while (head != fast) {
            head = head.next;
            fast = fast.next;
        }
        return head;
    }
```