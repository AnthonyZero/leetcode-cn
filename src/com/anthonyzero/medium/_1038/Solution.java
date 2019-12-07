package com.anthonyzero.medium._1038;

import com.anthonyzero.tools.BTS;
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
        BTS bts = new BTS(); //创建一个二分搜索树
        int[] array = new int[]{4,1,6,0,2,5,7,3,8};
        for (int i = 0; i < array.length; i++) {
            bts.addNode(array[i]);
        }
        bts.inOrder(); //中序遍历
        System.out.println();

        TreeNode root = solution.bstToGst(bts.root);
        bts.inOrderNode(root);
    }
}
