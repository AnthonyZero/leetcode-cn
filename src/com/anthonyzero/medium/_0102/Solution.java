package com.anthonyzero.medium._0102;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.TreeNode;

import java.util.*;

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int nodeSize = queue.size(); //当前层节点个数
            List<Integer> floor = new ArrayList<>();
            for (int i = 0; i < nodeSize; i++) {
                TreeNode node = queue.remove();
                floor.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(floor);
        }
        return list;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.addNode(3);
        bst.addNode(2);
        bst.addNode(4);
        bst.addNode(1);
        bst.addNode(5);
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.levelOrder(bst.root);
        System.out.println(lists);
    }

}
