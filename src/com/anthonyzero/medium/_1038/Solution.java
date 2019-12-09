package com.anthonyzero.medium._1038;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.TreeNode;

public class Solution {

    private int preValue = 0; //前一个值
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        bstToGst(root.right);
        root.val = root.val + preValue; //赋予新值
        preValue = root.val; //更新下一个节点 要加的值
        bstToGst(root.left);
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        BST bst = new BST(); //创建一个二分搜索树
        int[] array = new int[]{4,1,6,0,2,5,7,3,8};
        for (int i = 0; i < array.length; i++) {
            bst.addNode(array[i]);
        }
        bst.inOrder(); //中序遍历
        System.out.println();

        TreeNode root = solution.bstToGst(bst.root);
        bst.inOrderNode(root);
    }
}
