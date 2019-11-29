package com.anthonyzero.easy._0232;

import java.util.Stack;

public class Solution {


     class MyQueue {

        private Stack<Integer> inStack;
        private Stack<Integer> outStack;
        //
        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        //入队  每次维护输出栈即可  把输出栈的数据—>输入栈 压入元素 然后输入栈数据 -->输出栈
        public void push(int x) {
            while(!outStack.empty()) {
                inStack.push(outStack.pop());
            }

            inStack.push(x);  //这时候是输入栈完整数据

            while(!inStack.empty()) {  //把输入栈的数据 压入到输出栈 这时候输出栈就是我们的队列 栈顶就是队首
                outStack.push(inStack.pop());
            }
        }

        //出队
        public int pop() {
            return outStack.pop();
        }

        //队首
        public int peek() {
            return outStack.peek();
        }

        //判断是否为空
        public boolean empty() {
            return outStack.empty();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution.MyQueue queue = solution.new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // 返回 1
        System.out.println(queue.pop());;   // 返回 1
        System.out.println(queue.empty());; // 返回 false。
    }
}
