package com.anthonyzero.easy._0933;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    class RecentCounter {
        private Queue<Integer> queue;
        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            if(queue.isEmpty()) {
                queue.add(t);
            } else {
                //时间超过的 出队
                int diff = t - 3000;
                while(!queue.isEmpty() && queue.peek() < diff) {
                    queue.poll();
                }
                queue.add(t);
            }
            return queue.size();
        }
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new Solution().new RecentCounter();
        System.out.println(recentCounter.ping(642));
        System.out.println(recentCounter.ping(1849));
        System.out.println(recentCounter.ping(4921));
        System.out.println(recentCounter.ping(5936));
        System.out.println(recentCounter.ping(5937));
    }
}
