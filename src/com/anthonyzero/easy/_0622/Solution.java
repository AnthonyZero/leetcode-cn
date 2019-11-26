package com.anthonyzero.easy._0622;

import java.util.Arrays;

public class Solution {

    //不考虑扩容情况
    class MyCircularQueue {
        int[] data;  //data.length = k+1 保存k个元素
        int front;
        int tail;
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            data = new int[k + 1]; //预留一个位置（用于判断队列是否满的情况） 不存元素(并不是指数组不存的是最后一个元素)
            front = 0;
            tail = 0;
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            data[tail] = value;
            tail = (tail + 1) % data.length; //移动一个位置
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            data[front] = 0;
            front = (front + 1) % data.length;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return data[front];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if(isEmpty()) {
                return -1;
            }
            if (tail == 0) {
                return data[data.length - 1];
            } else {
                return data[tail - 1];
            }
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return front == tail;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return (tail + 1) % data.length == front;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution.MyCircularQueue circularQueue = solution.new MyCircularQueue(3);

        System.out.println(circularQueue.enQueue(1));

        System.out.println(circularQueue.enQueue(2));

        System.out.println(circularQueue.enQueue(3));
        System.out.println(Arrays.toString(circularQueue.data));

        System.out.println(circularQueue.enQueue(4));

        System.out.println(circularQueue.Rear());

        System.out.println(circularQueue.isFull());

        System.out.println(circularQueue.deQueue());
        System.out.println(Arrays.toString(circularQueue.data));

        System.out.println(circularQueue.enQueue(4));
        System.out.println(Arrays.toString(circularQueue.data));

        System.out.println(circularQueue.Rear());
        System.out.println(Arrays.toString(circularQueue.data));

        circularQueue.deQueue();
        circularQueue.enQueue(5);
        System.out.println(Arrays.toString(circularQueue.data));
    }
}
