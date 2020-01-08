package com.anthonyzero.medium._0215;

import java.util.PriorityQueue;

public class Solution {

    public int findKthLargest(int[] nums, int k) {
        // 最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int num : nums) {
            //每次都入队 数字较小的在队头
            queue.add(num);
            if (queue.size() > k) {
                //当超过了k个容量 较小值出队
                queue.remove();
            }
        }
        //最后维护了一个k 容量的最小堆  堆顶是前k个最大的元素
       return queue.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(new Solution().findKthLargest(nums, 4));
    }
}
