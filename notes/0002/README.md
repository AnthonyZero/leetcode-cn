## 2.两数相加

### 题目描述
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例:
```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```

### 思路
使用变量catty来跟踪进位，并从最低有效位的两个链表表头开始模拟逐位相加的过程. 

每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值

如果两个链表全部遍历完毕后，进位值为 1，则在新链表最前方添加节点 1
```   
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode vitural = new ListNode(-1);
        ListNode pre = vitural;
        int catty = 0; //进位
        int sum = 0;
        while(!(l1 == null && l2 == null)) {
            // 简化上面的步骤
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            sum = x + y + catty;
            if (sum >= 10) {
                pre.next = new ListNode(sum - 10);
                catty = 1;
            } else {
                pre.next = new ListNode(sum);
                catty = 0;
            }
            pre = pre.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //最高位
        if(catty > 0) {
            pre.next = new ListNode(catty);
        }
        return vitural.next;
    }
```