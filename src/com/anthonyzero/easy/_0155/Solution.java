package com.anthonyzero.easy._0155;

import java.util.Stack;

public class Solution {

    class MinStack {
        private int min = Integer.MAX_VALUE; //最小值
        private Stack<Integer> stack;
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (x <= min) {
                //更新最小值 顺便要前一个最小值压入栈
                stack.push(min);
                stack.push(x);
                min = x;
            } else {
                stack.push(x);
            }
        }

        public void pop() {
            if (stack.peek() == min) {
                //如果要出栈的元素是我们的最小值
                stack.pop(); //最小值出栈
                min = stack.pop();  //更新最小值 为前一个最小值
            } else {
                stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution.MinStack stack = solution.new MinStack();
        stack.push(0);
        stack.push(1);
        stack.push(0);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }
}
