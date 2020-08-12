## 剑指Offer 42.连续子数组的最大和

### 题目描述
输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

示例:
```
输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

提示：

* 1 <= arr.length <= 10^5
* -100 <= arr[i] <= 100

### 思路
利用前缀和求解：在迭代数组的过程中，不断更新前缀和中的最小值 以及要求结果的最大值。

如果前缀和最小值小于0，那么此时的前缀和减去它肯定有增益效果，把这个差值跟当前最大值比较取较大值。
如果前缀和最小值大于等于0，那么减去它没有增益效果，就比较当前所在位置的前缀和 和当前最大值取最大。

这里注意前缀和最小值的取值坐标一定要比当前所在位置的前缀和要小，所以这初始化的时候minArrSub和res有不同之处错开一位，迭代的
时候也是要注意是sum - nums[i]。 两个坐标i,j 一定是满足i < j的。

> 补充：[0,j]前缀和 - [0,i]前缀和 = [i,j]前缀和（也是连续子数组）
```   
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int minArrSub = 0; //前缀和中的最小值
        int res = nums[0]; //目标最大值
        int sum = nums[0]; //当前所在位置的前缀和
        for(int i = 1; i < nums.length; i++) {
            sum += nums[i];
            minArrSub = Integer.min(sum - nums[i], minArrSub);
            if(minArrSub < 0) {
                res = Integer.max(sum - minArrSub, res);
            } else {
                res = Integer.max(sum, res);
            }
        }
        return res;
    }
```