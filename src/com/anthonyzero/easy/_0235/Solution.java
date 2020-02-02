package com.anthonyzero.easy._0235;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.TreeNode;

public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] arr = new int[]{6,2,8,0,4,7,9,3,5};
        for (int i = 0; i < arr.length; i++) {
            bst.addNode(arr[i]);
        }
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        System.out.println(new Solution().lowestCommonAncestor(bst.root, p, q).val);
    }
}
