package com.anthonyzero.medium._0707;

public class Solution {

    class MyLinkedList {

        private int size;
        private ListNode dummyHead;

        public MyLinkedList() {
            dummyHead = new ListNode(-1);
            size = 0;
        }


        public int get(int index) {
            if (index >= size) {
                return -1;
            }
            ListNode pre = dummyHead;
            for (int i = 0; i <= index; i++) {
                pre = pre.next;
            }
            return pre.val;
        }


        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }


        public void addAtIndex(int index, int val) { //index 从0开始
            if (index > size)
                return;
            else {
                ListNode pre = dummyHead;
                for (int i = 0; i < index; i++) {
                    pre = pre.next;
                }
                ListNode node = new ListNode(val);
                node.next = pre.next;
                pre.next = node;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            ListNode pre = dummyHead;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            ListNode delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new Solution().new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        System.out.println(linkedList.get(1));            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        System.out.println(linkedList.get(1));            //返回3
    }

}
