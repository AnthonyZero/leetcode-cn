package com.anthonyzero.medium._0105;

import com.anthonyzero.tools.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<Integer,Integer> map = new HashMap<>(); //中序
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart]; //根的值
        int index = map.get(rootVal);  //根在中序遍历中的 位置
        TreeNode root = new TreeNode(rootVal);
        int offset = index - 1 - inStart; //偏移量

        root.left = buildTree(preorder, preStart + 1, preStart + 1 + offset, inorder, inStart, index - 1);
        root.right = buildTree(preorder, preStart + 1 + offset + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }
}
