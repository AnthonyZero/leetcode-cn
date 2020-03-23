package com.anthonyzero.easy._0122;

public class Solution {

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println(new Solution().maxProfit(prices));
    }
}
