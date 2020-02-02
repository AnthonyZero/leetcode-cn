package com.anthonyzero.easy._0257;

import com.anthonyzero.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    //二叉树的所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        if (root.left == null && root.right == null) {
            //叶子节点
            ArrayList<String> list = new ArrayList<>();
            list.add(String.valueOf(root.val));
            return list;
        }
        //合并左右子树的结果
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < left.size(); i++) {
            result.add(root.val + "->" + left.get(i));
        }
        for (int i = 0; i < right.size(); i++) {
            result.add(root.val + "->" + right.get(i));
        }
        return result;
    }
}
