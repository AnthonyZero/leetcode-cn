package com.anthonyzero.medium._0435;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    //第0列是start 第1列是end
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 按 end 升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1]; //按照end 升序排列
            }
        });
        // 至少有一个区间不相交
        int res = 1;
        // 第一个区间已使用
        int pre = 0;
        //从第二个区间开始
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            int start = interval[0]; //当前区间start
            if (start >= intervals[pre][1]) { //大于等于前一个区间end
                // 找到一个合适的区间 继续寻找下一个选择的区间
                res++;
                pre = i; //更新pre
            }
        }
        //总区间数 - 互不相交的区间数 = 需要移除的数
        return intervals.length - res;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[4][2];
        int[] col = new int[]{1,2,3,1};
        for(int i = 0; i < 4; i++) {
            intervals[i][0] = col[i];
        }
        col = new int[]{2,3,4,3};
        for(int i = 0; i < 4; i++) {
            intervals[i][1] = col[i];
        }
        for(int i = 0; i < intervals.length; i++) {
            System.out.println(Arrays.toString(intervals[i]));
        }
        System.out.println(new Solution().eraseOverlapIntervals(intervals));
    }
}
