package com.anthonyzero.easy._0110;

import com.anthonyzero.tools.TreeNode;

public class Solution {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        int abs = Math.abs(leftHeight - rightHeight); //先看节点的左右子树高度 是否相差1以上
        if (abs <= 1) {
            //在1范围内 说明当前节点为根的树暂时是平衡二叉树   那么继续看它的左右节点 同样是不是平衡二叉树
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    //获取树的最大深度
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
