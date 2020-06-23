package com.anthonyzero.easy._0067;

public class Solution {

    public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer("");
        int ca = 0; //是否有进位
        //两个字符串 从后往前遍历
        for(int i = a.length() - 1, j = b.length() - 1; i >=0 || j>=0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0; //在a字符串未越界前提下 sum加元素值 1或者0
            sum += j >= 0 ? b.charAt(j) - '0' : 0; //在b字符串未越界前提下 sum添加元素值 1或者0
            sb.append(sum % 2);
            ca = sum / 2;
        }
        if(ca == 1) {
            sb.append("1");
        }
        //字符串反转
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().addBinary("1010", "1011"));
    }
}
