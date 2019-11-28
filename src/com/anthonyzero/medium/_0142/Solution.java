package com.anthonyzero.medium._0142;

import com.anthonyzero.tools.ListNode;

public class Solution {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; //没有环返回null
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break; //停留在相遇点 环内 slow和fast
            }
        }
        if(slow != fast) { //没相遇返回null 代表没环
            return null;
        }

        //有环 寻找环的入环第一个节点
        while (head != fast) {
            head = head.next;
            fast = fast.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,0,-4};
        ListNode head = new ListNode(arr);
        head.next.next.next.next = head.next;
        /*head.next.next = head;*/
        System.out.println(new Solution().detectCycle(head).val);
    }
}
