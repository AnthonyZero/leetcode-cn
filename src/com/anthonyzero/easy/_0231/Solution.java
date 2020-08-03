package com.anthonyzero.easy._0231;

public class Solution {

//    public boolean isPowerOfTwo(int n) {
//        if (n == 0) {
//            return false;
//        }
//        if(n == 1 || n == 2) {
//            return true;
//        } else {
//            //n > 2情况 继续整除下去 或者 不能除以2了
//            if (n % 2 == 0) {
//                return isPowerOfTwo(n / 2);
//            } else {
//                return false;
//            }
//        }
//    }

    public boolean isPowerOfTwo(int n) {
        if (n == 0)  {
            return false;
        }
        long x = n; //-2147483648
        return (x & (x - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPowerOfTwo(16));
        System.out.println(new Solution().isPowerOfTwo(0));
        System.out.println(new Solution().isPowerOfTwo(-2147483648));
    }
}
