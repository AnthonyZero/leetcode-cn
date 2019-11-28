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

    //递归方式删除
//    public ListNode removeElements(ListNode head, int val) {
//        if (head == null) {
//            return null;
//        }
//        ListNode listNode = removeElements(head.next, val); //后面已经完成了目标 删除了目标值的 链表（返回的头节点）
//        if (head.val == val) {
//            head = listNode;  //代表删除当前头
//        } else {
//            head.next = listNode; //不删除 关联起来
//        }
//        return head;
//    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,6,3,4,5,6};
        ListNode head = new ListNode(arr); //创建一个链表
        System.out.println(head);
        ListNode listNode = new Solution().removeElements(head, 6);
        System.out.println(listNode);
    }
}
