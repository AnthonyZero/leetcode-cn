package com.anthonyzero.hard._0051;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    private boolean cols[]; //每列占用情况
    private boolean leftLower[]; //左下->右上对角线占用情况 有2*n-1条对角线
    private boolean leftUpper[]; //左上—>右下对角线占用情况 有2*n-1条对角线
    //对于一个n*n的坐标系上  左下->右上对角线中 在同一条上对角线的坐标x,y 满足x+y 都是同样值 且是leftLower数组的下标
    //对于一个n*n的坐标系上  左上—>右下对角线中 在同一条上对角线的坐标x,y 满足x-y+n-1 都是同样值 且是leftUpper数组的下标

    private List<List<String>> res = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        cols = new boolean[n];
        leftLower = new boolean[2*n+1];
        leftUpper = new boolean[2*n+1];
        //从第1,2.. n 行开始放皇后旗子
        putQueues(n, 0, new LinkedList<>()); //每一行都要有一个皇后Q
        return res;
    }

    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置, rows表示每一行中具体哪一列摆放
    private void putQueues(int n, int index, LinkedList<Integer> rows) {
        if (index == n) {
            //表示每一行都已经有了一个位置，已找到问题的一个解
            List<String> list = new ArrayList<>();
            for(int i = 0; i < rows.size(); i++) {
                char[] charArray = new char[n];
                Arrays.fill(charArray, '.'); //每一行初始化为.
                charArray[rows.get(i)] = 'Q'; //那一列放Q
                list.add(new String(charArray));
            }
            res.add(list);
            return;
        }

        //考虑在index此行中，从第0列到n-1列放置Q, i表示哪一列
        for(int i = 0; i < n; i++) { //每列都试 循环完了才递归返回

            // 3条线 都没占用才继续搜索下去
            if (!cols[i] && !leftLower[index + i] && !leftUpper[index - i + n - 1]) {
                rows.addLast(i);
                cols[i] = true; //此列占用
                //此时的坐标x = index, y = i;  两个对角线占用
                leftLower[index + i] = true;
                leftUpper[index - i + n - 1] = true;
                //继续下一行放置Q
                putQueues(n, index + 1, rows);
                //递归返回时 回溯 恢复状态
                rows.removeLast();
                cols[i] = false;
                leftLower[index + i] = false;
                leftUpper[index - i + n - 1] = false;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> res = (new Solution()).solveNQueens(n);
        for(List<String> board: res) {
            for(String s: board)
                System.out.println(s);
            System.out.println();
        }
    }

}
