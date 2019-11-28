package com.anthonyzero.easy._0083;


import com.anthonyzero.tools.ListNode;

public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        while(pre.next != null) {
            if (pre.val == pre.next.val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,3,3,4,4};
        ListNode head = new ListNode(arr);
        ListNode listNode = new Solution().deleteDuplicates(head);
        System.out.println(listNode);
    }
}
