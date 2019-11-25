package com.anthonyzero.easy._0118;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            if (i == 0) {
                list = Arrays.asList(1);
            } else {
                List<Integer> pre = data.get(i - 1);
                for (int j = 0; j <= i; j++) {
                    if (j == 0) { //pre第一元素
                        list.add(pre.get(0));
                    } else if (j == i) {  //pre最后一个元素 pre[size-1]
                        list.add(pre.get(j - 1));
                    } else {
                        list.add(pre.get(j-1) + pre.get(j));
                    }
                }
            }
            data.add(list);
        }
        return data;
    }

    public static void main(String[] args) {
        List<List<Integer>> data = new Solution().generate(5);
        System.out.println(data);
    }
}
