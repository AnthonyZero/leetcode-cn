package com.anthonyzero.easy._0237;

public class Solution {

    public void deleteNode(ListNode node) {
        node.val = node.next.val; //把下一个节点的值赋给当前节点，然后将当前节点指向下下个节点 变相删除
        node.next = node.next.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
