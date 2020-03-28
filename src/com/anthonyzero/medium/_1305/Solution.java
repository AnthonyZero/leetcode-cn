package com.anthonyzero.medium._1305;

import com.anthonyzero.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        //得到两个树的排序集合
        List<Integer> rootList1 = new ArrayList<>();
        List<Integer> rootList2 = new ArrayList<>();
        dfs(root1, rootList1);
        dfs(root2, rootList2);
        //对两个已排序好的集合 进行归并（归并排序中重要的归并过程）
        int left = 0;
        int right = 0;
        List<Integer> res = new ArrayList<>();
        while(left < rootList1.size() && right < rootList2.size()) {
            //哪个比较小添加进去
            if (rootList1.get(left) < rootList2.get(right)) {
                res.add(rootList1.get(left));
                left++;
            } else {
                res.add(rootList2.get(right));
                right++;
            }
        }
        //保证其中剩余的元素添加进去
        while(left < rootList1.size()) {
            res.add(rootList1.get(left));
            left++;
        }
        while(right < rootList2.size()) {
            res.add(rootList2.get(right));
            right++;
        }
        return res;
    }

    //中序遍历 得到排序的集合
    private void dfs(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
}
