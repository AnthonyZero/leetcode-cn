package com.anthonyzero.medium._0147;

import com.anthonyzero.tools.ListNode;

public class Solution {

    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0); //虚拟头节点
        dummyHead.next = head;

        ListNode pre = head; //待插入节点的前一个节点
        ListNode cur = head.next; //待插入节点 从第二个元素开始
        while(cur != null) {
            //待插入元素的比前面（已排好顺序）的最大节点值要小,说明要寻找插入位置，可以插入要前面去
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

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{-1,5,3,4,0});
        ListNode pre = new Solution().insertionSortList(head);
        while(pre != null) {
            System.out.print(pre.val + "->");
            pre = pre.next;
        }
    }
}
