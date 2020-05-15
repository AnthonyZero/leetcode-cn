## 148.排序链表

### 题目描述
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

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
归并排序思路：时间复杂度可达到O(nlogn),根据链表特性,通过修改引用来更改节点顺序，无需像数组一样开辟额外空间.可以达到O(1)的复杂度。

利用归并的思想，递归地将当前链表分为两段，然后进行归并merge，分两段的方法是用两个指针，一个快指针每次走两步，一个慢指针走一步，走的快的走到了末尾或者末尾后面，然后慢的所在位置就是中间位置，进行断开后就分成了两段时。
归并的时候把两段头部节点值比较，用一个指针cur指向较小的，且记录第一个节点，然后两段的头一步一步向后走，cur也一直向后走，总是指向较小节点，直至其中一段先走完结束，然后处理剩下的一段元素。最后返回记录的头即可。
```   
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode listNode = sort(head);
        return listNode;
    }

    private ListNode sort(ListNode head) {
        //只有一个元素的时候 递归结束 返回
        if (head.next == null) {
            return head;
        }
        //找到链表的中点 利用快慢指针
        ListNode pre = null; //在slow的前面
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) { //保证快指针可以走两步
            pre = slow;
            slow = slow.next; //慢指针 走一步
            fast = fast.next.next;  //快指针走两步
        }
        pre.next = null; //断开 一分为二 两个链表
        ListNode l = sort(head); //l已排好序
        ListNode r = sort(slow); //r已拍好序
        return merge(l, r);
    }


    /**
     * 对于两个已经各自有序的链表而已 进行归并操作，返回新的链表
     * @param l
     * @param r
     * @return
     */
    private ListNode merge(ListNode l, ListNode r) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while(l != null && r != null) {
            if(l.val < r.val) {
                cur.next = l; //链表连接
                l = l.next;
                cur = cur.next;
            } else{
                cur.next = r;
                r = r.next;
                cur = cur.next;
            }
        }
        // 存在其中一段剩余 需要连接上
        if(l != null) {
            cur.next = l;
        }
        if(r != null) {
            cur.next = r;
        }
        return dummyHead.next;
    }
```