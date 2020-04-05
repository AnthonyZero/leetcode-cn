package com.anthonyzero.easy._0101;

import com.anthonyzero.tools.TreeNode;

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isMirror(root, root);
    }

    //判断两个树 是否是对称的 （两个树 每个节点值 都比较是否相等）
    private boolean isMirror(TreeNode p, TreeNode q) {
        //都为空 那么肯定是对称的
        if(p == null && q == null) {
            return true;
        }
        //其中一个为空 不对称
        if(p == null || q == null) {
            return false;
        }
        //当前根节点相同的前提下
        if (p.val == q.val) {
            //要判断p的左树与q的右树对称  且 p的右树与q的左树对称
            return isMirror(p.left, q.right) && isMirror(p.right, q.left);
        }
        return false;
    }

}
