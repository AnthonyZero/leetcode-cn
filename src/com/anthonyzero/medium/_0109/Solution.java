package com.anthonyzero.medium._0109;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.ListNode;
import com.anthonyzero.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

   /* public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        TreeNode root = searchMiddle(null, list, 0, list.size() - 1);
        return root;
    }

    //寻找中间值的工程 递归
    private TreeNode searchMiddle(TreeNode root, List<Integer> list, int i, int j) {
        if (i == j) {
            return insertNode(root, list.get(i));
        } else if (i > j) {
            return root;
        } else {
            // i < j;
            int middle = (i + j + 1) / 2;
            TreeNode node = insertNode(root, list.get(middle));
            searchMiddle(node, list, i, middle - 1);
            searchMiddle(node, list, middle + 1, j);
            return node;
        }
    }

    private TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else if (val > root.val) {
            root.right = insertNode(root.right, val);
        }
        return root;
    }*/

    private List<Integer> list = new ArrayList<>();

    public TreeNode sortedListToBST(ListNode head) {
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        TreeNode root = createBST(0, list.size() - 1);
        return root;
    }

    private TreeNode createBST(int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            TreeNode node = new TreeNode(list.get(left));
            return node;
        } else {
            int middle = (left + right + 1) / 2;
            TreeNode root = new TreeNode(list.get(middle));
            root.left = createBST(left, middle - 1);
            root.right = createBST(middle + 1, right);
            return root;
        }
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(new int[] {-10, -3, 0, 5, 9});
        Solution solution = new Solution();
        TreeNode root = solution.sortedListToBST(head);
        BST bst = new BST();
        bst.inOrderNode(root);
    }
}
