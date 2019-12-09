package com.anthonyzero.easy._0206;

import com.anthonyzero.tools.ListNode;

public class Solution {

    /*public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head;
        ListNode node = head.next;
        ListNode pre = head;
        head.next = null; //断开头节点
        while (node != null) {
            newHead = node; //newHead移动一步
            node = node.next; //node移动一步
            newHead.next = pre; //改变指向
            pre = newHead; //更新pre
        }
        return newHead;
    }*/

    /*public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }*/

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[] {1,2,3,4,5});
        Solution solution = new Solution();
        ListNode listNode = solution.reverseList(head);
        while(listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
    }
}
