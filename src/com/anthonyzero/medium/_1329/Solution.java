package com.anthonyzero.medium._1329;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length; //多少行 3
        int n = mat[0].length; //多少列 4
        for(int i = 0; i < n - 1; i++) { //排序右上部分的 对角线 有n条对角线 不要右上角的
            //起点横坐标为0 纵坐标y从0到 n-1
            int x = 0;
            int y = i;
            List<Integer> list = new ArrayList<>();
            while(x < m && y < n) {
                list.add(mat[x++][y++]);
            }
            Collections.sort(list);
            x = 0;
            y = i;
            for(int temp : list) {
                mat[x++][y++] = temp;
            }
        }
        for(int i = 1; i < m - 1; i++) { //排序左下部分的 对角线 有m-1条对角线 不要左下角的
            //起点横坐标为i从1到 m-1 纵坐标为0
            int x = i;
            int y = 0;
            List<Integer> list = new ArrayList<>();
            while(x < m && y < n) {
                list.add(mat[x++][y++]);
            }
            Collections.sort(list);
            x = i;
            y = 0;
            for(int temp : list) {
                mat[x++][y++] = temp;
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {3,3,1,1},
                {2,2,1,2},
                {1,1,1,2}
        };
        mat = new Solution().diagonalSort(mat);
        for(int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }
}
