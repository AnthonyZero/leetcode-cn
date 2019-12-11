package com.anthonyzero.hard._0099;

import com.anthonyzero.tools.TreeNode;

import java.util.*;

public class Solution {

    private List<Integer> result = null;
    private int index;

    public void recoverTree(TreeNode root) {
        result = inOrderList(root, new ArrayList<>());
        Collections.sort(result); //默认升序排序
        recoverTreeNode(root);
    }

    private void recoverTreeNode(TreeNode root) {
        if (root == null) {
            return;
        }
        recoverTreeNode(root.left);
        if (root.val != result.get(index)) {
            root.val = result.get(index);
        }
        index++;
        recoverTreeNode(root.right);
    }

    /**
     * 获取中序遍历集合
     * @param root
     * @param list
     * @return
     */
    private List<Integer> inOrderList(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        inOrderList(root.left, list);
        list.add(root.val);
        inOrderList(root.right, list);
        return list;
    }
}
