package com.anthonyzero.easy._0237;

import com.anthonyzero.tools.ListNode;

public class Solution {

    public void deleteNode(ListNode node) {
        node.val = node.next.val; //把下一个节点的值赋给当前节点，然后将当前节点指向下下个节点 变相删除
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,5,1,9};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        ListNode delNode = head.next; //删除5这个节点
        new Solution().deleteNode(delNode);
        System.out.println(head);
    }
}
