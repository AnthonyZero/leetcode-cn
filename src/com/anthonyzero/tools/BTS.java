package com.anthonyzero.tools;

/**
 * 二叉搜索树
 */
public class BTS {

    public TreeNode root;

    public BTS() {
        root = null;
    }


    public void addNode(int value) {
        root = addNode(root, value);
    }

    // 以当前节点出发 添加value
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
