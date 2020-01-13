package com.anthonyzero.medium._0002;

import com.anthonyzero.tools.ListNode;

public class Solution {

    /*public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode vitural = new ListNode(-1);
        ListNode pre = vitural;
        int catty = 0; //进位
        int sum = 0;
        while(!(l1 == null && l2 == null)) {
            if (l1 == null && l2 != null) {
                sum = l2.val + catty;
                if (sum >= 10) {
                    pre.next = new ListNode(sum - 10);
                    catty = 1;
                } else {
                    pre.next = new ListNode(sum);
                    catty = 0;
                }
                l2 = l2.next;
                pre = pre.next;
            } else if (l1 != null && l2 == null) {
                sum = l1.val + catty;
                if (sum >= 10) {
                    pre.next = new ListNode(sum - 10);
                    catty = 1;
                } else {
                    pre.next = new ListNode(sum);
                    catty = 0;
                }
                l1 = l1.next;
                pre = pre.next;
            } else {
                //两个都有值
                sum = l1.val + l2.val + catty;
                if (sum >= 10) {
                    pre.next = new ListNode(sum - 10);
                    catty = 1;
                } else {
                    pre.next = new ListNode(sum);
                    catty = 0;
                }
                l1 = l1.next;
                l2 = l2.next;
                pre = pre.next;
            }
        }
        if(catty > 0) {
            pre.next = new ListNode(catty);
        }
        return vitural.next;
    }*/

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode vitural = new ListNode(-1);
        ListNode pre = vitural;
        int catty = 0; //进位
        int sum = 0;
        while(!(l1 == null && l2 == null)) {
            // 简化上面的步骤
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            sum = x + y + catty;
            if (sum >= 10) {
                pre.next = new ListNode(sum - 10);
                catty = 1;
            } else {
                pre.next = new ListNode(sum);
                catty = 0;
            }
            pre = pre.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //最高位
        if(catty > 0) {
            pre.next = new ListNode(catty);
        }
        return vitural.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{9,9});
        ListNode l2 = new ListNode(new int[]{9,9,9});
        ListNode head = new Solution().addTwoNumbers(l1,l2);
        while(head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
