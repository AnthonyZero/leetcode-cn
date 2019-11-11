package com.anthonyzero.easy._0020;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {

    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')');
    }};

    public boolean isValid(String s) {
        if ("".equals(s)) {
            return true;
        }
        if (s.length() % 2 == 1) { //如果字符串长度是奇数 肯定不是无效字符串
            return false;
        }
        char[] target = s.toCharArray();
        Stack stack = new Stack();
        for (int i = 0; i < target.length; i++) {
            if (stack.isEmpty()) {
                stack.push(target[i]);
            } else {
                if (stack.size() > (s.length() - i)) { //如果栈的深度 已经大于了 剩下的需要处理的字符数
                    return false;
                }
                if (map.keySet().contains(stack.peek()) && map.get(stack.peek()).equals(target[i])) {
                    stack.pop();
                } else {
                    stack.push(target[i]);
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        String target = "()[]";
        System.out.println(solution.isValid(target));
    }
}
