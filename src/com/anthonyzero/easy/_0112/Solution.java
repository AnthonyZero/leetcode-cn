package com.anthonyzero.easy._0112;

import com.anthonyzero.tools.TreeNode;

public class Solution {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == sum; //递归终止条件 左右孩子为空才是叶子节点 到了叶子节点判断剩下的值是否等于sum
        }
        //每下一层 sum减去当前节点的值
        if (hasPathSum(root.left, sum - root.val)) {
            return true;
        }
        if (hasPathSum(root.right, sum - root.val)) {
            return true;
        }
        return false;
    }
}
