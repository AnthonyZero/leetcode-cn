package com.anthonyzero.easy._0141;

import com.anthonyzero.tools.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    /*public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while(head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }*/

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2};
        ListNode head = new ListNode(arr);
        head.next.next = head;
        System.out.println(new Solution().hasCycle(head));
    }
}
