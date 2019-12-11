package com.anthonyzero.hard._0099;

import com.anthonyzero.tools.TreeNode;

import java.util.*;

public class Solution {

    /*private List<Integer> result = null;
    private int index;

    public void recoverTree(TreeNode root) {
        result = inOrderList(root, new ArrayList<>());
        Collections.sort(result); //默认升序排序
        recoverTreeNode(root);
    }

    private void recoverTreeNode(TreeNode root) {
        if (root == null) {
            return;
        }
        recoverTreeNode(root.left);
        if (root.val != result.get(index)) {
            root.val = result.get(index);
        }
        index++;
        recoverTreeNode(root.right);
    }

    *//**
     * 获取中序遍历集合
     * @param root
     * @param list
     * @return
     *//*
    private List<Integer> inOrderList(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        inOrderList(root.left, list);
        list.add(root.val);
        inOrderList(root.right, list);
        return list;
    }*/

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode pre = null; //pre每次变化 = 二叉树中序遍历的顺序

    public void recoverTree(TreeNode root) {
        inOrder(root); //first 和second 已经赋值了
        int temp = first.val; //交换错误的两个节点的值
        first.val = second.val;
        second.val = temp;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (pre != null && pre.val > root.val) {
            if (first == null) { //first为空,首次找到前后大小不对的点
                first = pre;
                second = root;
            } else {
                second = root; //first不为空,第二次找到前后大小不对的点,只更新second
            }
        }
        pre = root; //更新前驱
        inOrder(root.right);
    }
}
