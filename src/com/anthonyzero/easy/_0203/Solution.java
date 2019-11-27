package com.anthonyzero.easy._0203;

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


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(6);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        Solution solution = new Solution();
        ListNode listNode = solution.removeElements(head, 6);
        while(listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
