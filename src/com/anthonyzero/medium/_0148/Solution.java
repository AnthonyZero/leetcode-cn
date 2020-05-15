package com.anthonyzero.medium._0148;

import com.anthonyzero.tools.ListNode;

public class Solution {

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

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{-1,5,3,4,0});
        ListNode sortHead = new Solution().sortList(listNode);
        while(sortHead != null) {
            System.out.print(sortHead.val + "->");
            sortHead = sortHead.next;
        }
    }
}
