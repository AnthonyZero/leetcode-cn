package com.anthonyzero.tools;

/**
 * 单向链表节点
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    // 链表节点的构造函数
    // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
    public ListNode(int[] arr){

        if(arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr can not be empty");

        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1 ; i < arr.length ; i ++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    // 以当前节点为头结点的链表信息字符串  当测试有环链表的时候 不能覆盖的toString()方法 会内存溢出
    /*@Override
    public String toString(){

        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            s.append(cur.val + "->");
            cur = cur.next;
        }
        s.append("NULL");
        return s.toString();
    }*/

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }
}
