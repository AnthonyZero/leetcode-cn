package com.anthonyzero.medium._0120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

//    private List<List<Integer>> list;
//    private int[][] memory;
//    public int minimumTotal(List<List<Integer>> triangle) {
//        if(triangle == null || triangle.size() == 0) {
//            return 0;
//        }
//        if (triangle.size() == 1) {
//            return triangle.get(0).get(0);
//        }
//        list = triangle;
//        memory = new int[list.size()][list.get(list.size() - 1).size()];
//        for(int i = 0; i < memory.length; i++) {
//            Arrays.fill(memory[i], -1);
//        }
//        return minimumTotal(0, 0);
//    }
//
//    //从index行pos列开始 到三角形底部的 最小路径和
//    private int minimumTotal(int index, int pos) {
//        if (index == list.size() - 1) {
//            //底部 只有一行 返回所在位置值
//            return list.get(index).get(pos);
//        }
//        if(memory[index][pos] != -1) {
//            return memory[index][pos];
//        }
//        //两个方向 取较小值
//        memory[index][pos] = Integer.min(minimumTotal(index + 1, pos),
//                    minimumTotal(index + 1, pos + 1)) + list.get(index).get(pos);
//        return memory[index][pos];
//    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int size = triangle.size();
        int[] dp = new int[triangle.get(size - 1).size()]; //一维数组，长度取决于三角形最后一行的长度（列宽）
        for(int i = 0; i < dp.length; i++) {
            //初始化 从底部开始往三角形顶部开始走。dp初始值为底部最后一行的值
            dp[i] = triangle.get(size - 1).get(i);
        }
        //往上走，更新dp数组的值，递推的每一行只依赖于前一行的值 这里O(n)空间即可解决
        for(int i = size - 2; i >= 0; i--) { //从倒数第二行开始 往上 dp数组的每一项值 开始累加
            for(int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Integer.min(dp[j], dp[j + 1]); //此行的当前坐标值 + 下面一行的两个中的较小值
            }
        }
        return dp[0];
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
