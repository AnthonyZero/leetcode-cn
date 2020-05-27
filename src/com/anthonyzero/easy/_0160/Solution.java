package com.anthonyzero.easy._0160;

import com.anthonyzero.tools.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {

//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        Set<ListNode> set = new HashSet<>();
//        while(headA != null || headB != null) {
//            if (headA != null) {
//                if(set.contains(headA)) {
//                    return headA;
//                }
//                set.add(headA);
//                headA = headA.next;
//            }
//            if (headB != null) {
//                if(set.contains(headB)) {
//                    return headB;
//                }
//                set.add(headB);
//                headB = headB.next;
//            }
//        }
//        return null;
//    }

    //两个指针
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA, nodeB = headB;
        while(nodeA != nodeB) {
            //如果有相交点 肯定会相遇的 他们走的路程是一样的
            nodeA = (nodeA == null ) ?  headB : nodeA.next;  //nodeA 到了末尾 指针指向headB 开始往后走
            nodeB = (nodeB == null) ? headA : nodeB.next;   //nodeB 到了末尾 指针指向headA 开始往后走
        }
        //while 退出的时候 nodeA = nodeB 有两种情况 一：相遇点 nodeA nodeB 同一个节点不等于null  二：都走完了，最后null == null 退出
        //如果相遇 nodeA
        return nodeA;
    }
}
