package com.anthonyzero.medium._0098;

import com.anthonyzero.tools.BTS;
import com.anthonyzero.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /*private List<Integer> list = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        inOrder(root);
        for(int i = 0; i < list.size()-1; i++) {
            if (list.get(i+1) <= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }*/

    private double min = -Double.MAX_VALUE;
    public  boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST(root.left)) {
            if (root.val > min) {
                min = root.val;
                return isValidBST(root.right);
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        BTS bts = new BTS();
        for (int i = 0; i < 3; i++) {
            bts.addNode(i);
        }
        Solution solution = new Solution();
        System.out.println(solution.isValidBST(bts.root));
    }
}
