package com.anthonyzero.easy._0225;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    class MyStack {
        private Queue<Integer> queue;
        private int top; //保存队尾数字 代表栈顶
        public MyStack() {
            queue = new LinkedList<>();
        }

        // 添加到队尾
        public void push(int x) {
            queue.offer(x);
            top = x;
        }

        //思路在于 把队列前面n-1个元素 出队后陆续先后顺序 重新入队。 那么队头元素就是 要删的元素（栈顶元素）
        public int pop() {
            int size = queue.size();
            for (int i = 0; i < size-1; i++) {
                int element = queue.poll(); //出队 队首第一个元素
                queue.offer(element);  //又入队
                top = element;  //更新栈顶元素
            }
            //那现在队首元素 就是top
            return queue.poll(); //移除队首元素 第一个元素
        }

        //获取栈顶元素  = 队尾
        public int top() {
            return top;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution.MyStack stack = solution.new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.empty());
    }
}
