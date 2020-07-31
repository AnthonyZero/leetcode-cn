package com.anthonyzero.easy._0633;

public class Solution {

//    //必须i 是long类型 不然超时
//    public boolean judgeSquareSum(int c) {
//        for(long i = 0; i * i <= c; i++) {
//            double j = Math.sqrt(c - i * i); //求根
//            int temp = (int)j; //强转整数
//            if(j == temp) {
//                //如果相等 说明求根的值原先就是整数
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean judgeSquareSum(int c) {
        for(long i = 0; i * i <= c; i++) {
            int j = c - (int)(i * i); //差值
            if(search(0, j, j)) {
                return true;
            }
        }
        return false;
    }

    //通过二分法找 找一个数的平方是否 等于target 存在返回true 不存在返回false
    public boolean search(long left, long right, int target) {
        while(left <= right) {
            long mid = left + (right - left) / 2;
            if(mid * mid == target) {
                return true;
            }
            if(mid * mid > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().judgeSquareSum(5));
        System.out.println(new Solution().judgeSquareSum(3));
        System.out.println(new Solution().judgeSquareSum(2));
        System.out.println(new Solution().judgeSquareSum(1000000000)); //true
    }
}
