package com.anthonyzero.tools;

/**
 * 二叉搜索树
 */
public class BST {

    public TreeNode root;

    public BST() {
        root = null;
    }


    public void addNode(int value) {
        root = addNode(root, value);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private TreeNode addNode(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.val) {
            node.left = addNode(node.left, value);
        } else if (value > node.val) {
            node.right = addNode(node.right, value);
        }
        return node;
    }

    // 中序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 指定节点 中序遍历
    public void inOrderNode(TreeNode root) {
        inOrder(root);
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }


    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

}
