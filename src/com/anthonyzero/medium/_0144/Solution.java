package com.anthonyzero.medium._0144;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

//    public List<Integer> preorderTraversal(TreeNode root) {
//        if (root == null) {
//            return new ArrayList<>();
//        }
//        List<Integer> list = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while(!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            list.add(node.val);
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//        }
//        return list;
//    }


    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    public static void main(String[] args) {
        BST bst = new BST(); //这使用特殊的二叉树（二叉搜索树）
        bst.addNode(3);
        bst.addNode(2);
        bst.addNode(4);
        bst.addNode(1);
        bst.addNode(5);
        Solution solution = new Solution();
        List<Integer> list = solution.preorderTraversal(bst.root);
        System.out.println(list);
    }
}
