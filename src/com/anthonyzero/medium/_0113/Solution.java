package com.anthonyzero.medium._0113;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private List<List<Integer>> res;
    /*public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        pathSum(root, sum, new ArrayList<>());
        return res;
    }

    public void pathSum(TreeNode root, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }
        //叶子节点
        if(root.left == null && root.right == null) {
            if(root.val == sum) {
                list.add(root.val);
                res.add(list);
            }
            return;  //递归返回
        }
        list.add(root.val);
        pathSum(root.left, sum - root.val, new ArrayList<>(list));
        pathSum(root.right, sum - root.val, new ArrayList<>(list));
    }*/


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        dfs(root, sum, new ArrayList<>());
        return res;
    }

    //回溯的方式
    public void dfs(TreeNode root, int sum, List<Integer> list) {
        if(root == null) {
            return;
        }
        list.add(root.val);
        sum = sum - root.val;
        if(root.left == null && root.right == null && sum == 0) {
            //叶子节点 深度搜索到底了
            res.add(new ArrayList<>(list)); //添加一个分支
            list.remove(list.size() - 1);
            return;  //递归返回
        }
        //只有两个选择 左树 右树
        dfs(root.left, sum, list);
        dfs(root.right, sum, list);
        //回溯(撤销上次添加根节点的选择)
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.addNode(13);
        bst.addNode(9);
        bst.addNode(15);
        bst.addNode(7);
        bst.addNode(14);
        bst.addNode(19);
        bst.addNode(2);
        bst.addNode(8);
        bst.addNode(16);
        bst.addNode(20);
        bst.levelOrder();
        List<List<Integer>> lists = new Solution().pathSum(bst.root, 37);
        System.out.println(lists);
    }
}
