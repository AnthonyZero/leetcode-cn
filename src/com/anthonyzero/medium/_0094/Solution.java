package com.anthonyzero.medium._0094;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> inorder = inorder(root, list);
        return inorder;
    }

    private List<Integer> inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
        return list;
    }

    public static void main(String[] args) {
        BST bst = new BST(); //这使用特殊的二叉树（二叉搜索树）
        for (int i = 10; i > 0; i--) {
            bst.addNode(i);
        }
        Solution solution = new Solution();
        List<Integer> list = solution.inorderTraversal(bst.root);
        System.out.println(list);
    }
}
