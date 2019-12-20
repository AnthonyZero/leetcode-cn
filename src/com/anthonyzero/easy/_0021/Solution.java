package com.anthonyzero.easy._0021;

import com.anthonyzero.tools.ListNode;

public class Solution {


    /*public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode pre = dummyNode;
        while(l1 != null || l2 != null) {
            if (l1 == null && l2 != null) {
                pre.next = new ListNode(l2.val);
                l2 = l2.next;
                pre = pre.next;
            } else if (l1 != null && l2 == null) {
                pre.next = new ListNode(l1.val);
                l1 = l1.next;
                pre = pre.next;
            } else {
                if (l1.val <= l2.val) {
                    pre.next = new ListNode(l1.val);
                    l1 = l1.next;
                    pre = pre.next;
                } else if (l1.val > l2.val) {
                    pre.next = new ListNode(l2.val);
                    l2 = l2.next;
                    pre = pre.next;
                } else {
                    pre.next = new ListNode(l1.val);
                    pre = pre.next;
                    pre.next = new ListNode(l1.val);
                    pre = pre.next;
                    l1 = l1.next;
                    l2 = l2.next;
                }
            }
        }
        return dummyNode.next;
    }*/

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode pre = dummyNode;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = new ListNode(l1.val);
                l1 = l1.next;
                pre = pre.next;
            } else if (l1.val > l2.val) {
                pre.next = new ListNode(l2.val);
                l2 = l2.next;
                pre = pre.next;
            } else {
                pre.next = new ListNode(l1.val);
                pre.next.next = new ListNode(l1.val);
                pre = pre.next.next;
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            pre.next = l2;
        } else {
            pre.next = l1;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{1,2,4});
        ListNode l2 = new ListNode(new int[]{1,3,4});
        Solution solution = new Solution();
        ListNode node = solution.mergeTwoLists(l1, l2);
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }
}
