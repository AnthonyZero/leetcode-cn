package com.anthonyzero.medium._0120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private List<List<Integer>> list;
    private int[][] memory;
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        list = triangle;
        memory = new int[list.size()][list.get(list.size() - 1).size()];
        for(int i = 0; i < memory.length; i++) {
            Arrays.fill(memory[i], -1);
        }
        return minimumTotal(0, 0);
    }

    //从index行pos列开始 到三角形底部的 最小路径和
    private int minimumTotal(int index, int pos) {
        if (index == list.size() - 1) {
            //底部 只有一行 返回所在位置值
            return list.get(index).get(pos);
        }
        if(memory[index][pos] != -1) {
            return memory[index][pos];
        }
        //两个方向 取较小值
        memory[index][pos] = Integer.min(minimumTotal(index + 1, pos),
                    minimumTotal(index + 1, pos + 1)) + list.get(index).get(pos);
        return memory[index][pos];
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {2},
                {3,4},
                {6,5,7},
                {4,1,8,3}
        };
        List<List<Integer>> triangle = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j < arr[i].length; j++) {
                tmp.add(arr[i][j]);
            }
            triangle.add(tmp);
        }
        System.out.println(new Solution().minimumTotal(triangle));
    }
}
