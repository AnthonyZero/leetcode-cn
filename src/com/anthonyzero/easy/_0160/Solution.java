package com.anthonyzero.easy._0160;

import com.anthonyzero.tools.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while(headA != null || headB != null) {
            if (headA != null) {
                if(set.contains(headA)) {
                    return headA;
                }
                set.add(headA);
                headA = headA.next;
            }
            if (headB != null) {
                if(set.contains(headB)) {
                    return headB;
                }
                set.add(headB);
                headB = headB.next;
            }
        }
        return null;
    }
}
