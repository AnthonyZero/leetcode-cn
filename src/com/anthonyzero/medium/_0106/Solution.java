package com.anthonyzero.medium._0106;

import com.anthonyzero.tools.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        return root;
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                               int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int val = postorder[postEnd]; //取后序最后一位 为根
        int index = map.get(val); //根在前序 中的位置。
        TreeNode root = new TreeNode(val);
        int postOffset = index - 1 - inStart;  //后序数组的偏移量 根据左右 根前提 不要根断开取左右子树

        root.left = buildTree(inorder, inStart, index - 1, postorder,
                postStart, postStart + postOffset);

        root.right = buildTree(inorder, index + 1, inEnd, postorder,
                postStart + postOffset + 1, postEnd - 1);
        return root;
    }
}
