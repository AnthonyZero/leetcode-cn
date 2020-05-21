package com.anthonyzero.medium._0470;

public class Solution {

    //生成[1,10]的随机数
//    public int rand10() {
//        int num = (rand7() - 1) * 7 + rand7(); //rand49 [1,49]
//        while(num > 10) {
//            num = (rand7() - 1) * 7 + rand7();
//        }
//        return num;
//    }

    //优化 只丢弃[41,49] 用[1,40] 取余 + 1
    public int rand10() {
        int num = (rand7() - 1) * 7 + rand7(); //rand49 [1,49]
        while (num > 40) {
            num = (rand7() - 1) * 7 + rand7();
        }
        return num % 10 + 1;  //[1,10]
    }

    // [1到7]
    public int rand7() {
        return (int)(Math.random()* 7) + 1;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            System.out.println(new Solution().rand10());
        }
    }
}
