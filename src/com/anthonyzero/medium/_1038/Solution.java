package com.anthonyzero.medium._1038;

import com.anthonyzero.tools.BTS;
import com.anthonyzero.tools.TreeNode;

public class Solution {

    private int preValue = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        bstToGst(root.right);
        root.val = root.val + preValue;
        preValue = root.val;
        bstToGst(root.left);
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        BTS bts = new BTS();
        int[] array = new int[]{4,1,6,0,2,5,7,3,8};
        for (int i = 0; i < array.length; i++) {
            bts.addNode(array[i]);
        }
        bts.inOrder();
        System.out.println();
        bts.preOrder();

        System.out.println();
        TreeNode root = solution.bstToGst(bts.root);
        bts.inOrderNode(root);
    }
}
