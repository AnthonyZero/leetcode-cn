package com.anthonyzero.medium._0046;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] used;

//    public List<List<Integer>> permute(int[] nums) {
//        if (nums.length == 0) {
//            return new ArrayList<>();
//        }
//        premutePath(new ArrayList<>(), nums);
//        return res;
//    }
//
//
//    private void premutePath(List<Integer> prefix, int[] nums) {
//        if (nums.length == 0) {
//            res.add(prefix);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            List<Integer> list = new ArrayList<>(prefix);
//            list.add(nums[i]);
//            premutePath(list, remove(nums, nums[i]));
//        }
//        return;
//    }
//
//
//    //删除指定元素
//    private int[] remove(int[] nums, Integer element) {
//        if (nums.length == 0) {
//            return new int[0];
//        }
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != element) {
//                list.add(nums[i]);
//            }
//        }
//        int[] array = new int[list.size()];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = list.get(i);
//        }
//        return array;
//    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, List<Integer> prefix) {
        if (prefix.size() == nums.length) {
            //到达树底部
            //res.add(prefix); //值传递 这样添加会导致递归返回的时候prefix会remove元素 得到的是空集合
            res.add(new ArrayList<>(prefix));
            return;
        }
        for (int i = 0; i < nums.length; i++) { //当开始循环到1 2... 的时候 因为有撤销选择恢复的操作 used数组全是false
            if (!used[i]) { //没使用过
                used[i] = true;
                prefix.add(nums[i]);
                backtrack(nums, prefix);
                //恢复状态 让其他递归路径 可利用(很重要)
                prefix.remove(Integer.valueOf(nums[i]));
                used[i] = false;
            }
        }
        return;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1,2,3}));
    }
}
