package com.anthonyzero.easy._0783;

import com.anthonyzero.tools.BST;
import com.anthonyzero.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

//    public List<Integer> list = new ArrayList<>();
//    public int minDiffInBST(TreeNode root) {
//        if(root == null) {
//            return -1;
//        }
//        inOrder(root);
//        int minDiff = Integer.MAX_VALUE;
//        for(int i = 1; i < list.size(); i++) {
//            //相邻元素的 差的绝对值
//            int differ = Math.abs(list.get(i) - list.get(i - 1));
//            if(differ < minDiff) {
//                minDiff = differ;
//            }
//        }
//        return minDiff;
//    }
//
//    //中序遍历 从小到到
//    private void inOrder(TreeNode root) {
//        if(root == null) {
//            return;
//        }
//        inOrder(root.left);
//        list.add(root.val);
//        inOrder(root.right);
//    }

    public Integer preElement; //遍历的过程中 前一个元素
    public int minDiff = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        if(root == null) {
            return -1;
        }
        inOrder(root);
        return minDiff;
    }

    // 中序遍历
    private void inOrder(TreeNode root) {
        if(root == null) {
            return;
        }
        inOrder(root.left);
        if(preElement == null) {
            preElement = root.val;
        } else {
            int differ = Math.abs(root.val - preElement);
            if (differ < minDiff) {
                minDiff = differ;
            }
            preElement = root.val;
        }
        inOrder(root.right);
    }


    public static void main(String[] args) {
        BST bst = new BST();
        int[] arr = new int[]{4,2,6,1,3};
        for(int i = 0; i < arr.length; i++) {
            bst.addNode(arr[i]);
        }
        System.out.println(new Solution().minDiffInBST(bst.root));
    }
}
