## 53.最大子序和

### 题目描述
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例 :
```
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

### 思路
贪心算法：最大和的连续子序列一定是以正数开始正数结束，先初始化当前最大值和结果最大值为第一个元素值。
然后迭代遍历该数组，如果当前遍历的元素值（>0）加起来 对结果curMax有增益效果，那么就加上此位置元素值。最大值肯定会增加。如果此元素(<0)加起来对结果curMax无增益效果, 要么从当前元素重新开始计数，要么加上此值继续下去，看这两个结果哪个最大选哪个。

每一步选择最优的，局部最优-》全局最优
```   
    /**
     * nums   2 3 -6 2 4
     * curMax 2 5 -1 2 6
     *  max   2 5  5 5 6
     */
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int curMax = nums[0];
        int max = curMax;
        for(int i = 1; i < nums.length; i++) {
            curMax = Integer.max(nums[i], curMax + nums[i]);
            max = Integer.max(curMax, max);
        }
        return max;
    }
```

