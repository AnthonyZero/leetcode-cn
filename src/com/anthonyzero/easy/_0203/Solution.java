package com.anthonyzero.easy._0203;

import com.anthonyzero.tools.ListNode;

public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        ListNode virtualNode = new ListNode(-1); //虚拟节点
        virtualNode.next = head;
        ListNode pre = virtualNode; //pre遍历
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next; //不用后移，因为可能下一个又是目标值
            } else {
                pre = pre.next;
            }
        }
        return virtualNode.next;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,6,3,4,5,6};
        ListNode head = new ListNode(arr); //创建一个链表
        System.out.println(head);
        ListNode listNode = new Solution().removeElements(head, 6);
        System.out.println(listNode);
    }
}
