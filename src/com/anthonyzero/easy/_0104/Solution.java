package com.anthonyzero.easy._0104;

import com.anthonyzero.tools.TreeNode;

public class Solution {

    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    private int maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        int leftDepth = maxDepth(root.left, depth);
        int rigthDepth = maxDepth(root.right, depth);
        return leftDepth < rigthDepth ? rigthDepth : leftDepth;
    }
}
