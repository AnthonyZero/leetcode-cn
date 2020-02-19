package com.anthonyzero.easy._0455;

import java.util.Arrays;

public class Solution {

//    public int findContentChildren(int[] g, int[] s) {
//        Arrays.sort(g);
//        Arrays.sort(s);
//
//        int res = 0;
//        int start = 0;
//        for (int i = 0; i < g.length; i++) {
//
//            for(;start < s.length; start++) {
//                if(s[start] >= g[i]) {
//                    //饼干大小能满足
//                    start++; //从下一块饼干开始
//                    res++;
//                    break; //跳出当前循环 看下个孩子
//                }
//            }
//        }
//        return res;
//    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int gi = 0;
        int si = 0;

        while(gi < g.length && si < s.length) {
            if (s[si] >= g[gi]) {
                //当前饼干尺寸能满足当前小朋友胃口
                res++;
                gi++;
                si++;
            } else {
                //当前饼干尺寸不满足 看下块饼干
                si++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] g = new int[]{2,3};
        int[] s = new int[]{1,2,3};
        System.out.println(new Solution().findContentChildren(g, s));
    }
}
