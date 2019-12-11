package com.anthonyzero.easy._0226;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.TreeNode;

public class Solution {


    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 左子树和右子树交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归翻转左右子树
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }



    public static void main(String[] args) {
        BST bst = new BST();
        bst.addNode(1);
        bst.addNode(-1);
        bst.addNode(2);
        bst.levelOrder(); //层序遍历

        System.out.println();
        Solution solution = new Solution();
        TreeNode treeNode = solution.invertTree(bst.root);
        bst.levelOrder();
    }
}
