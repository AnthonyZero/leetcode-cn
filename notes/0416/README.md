## 416.分割等和子集

### 题目描述
给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:

每个数组中的元素不会超过 100
数组的大小不会超过 200

示例 1:
```   
输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].

```

示例 2:   
```
输入: [1, 2, 3, 5]

输出: false

解释: 数组不能分割成两个元素和相等的子集.
```

### 思路1
自顶向下递归方式：典型的背包问题，在n个数字中选出一定的数字，填满sum/2的背包
```   
    private int[][] memery;
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            //不能平分 说明不存在两个子集的元素和相等
            return false;
        }
        //从n个数中选择和等于sum/2
        memery = new int[nums.length][sum / 2 + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                memery[i][j] = -1;
            }
        }
        return tryPartition(nums, 0, sum / 2);
    }

    private boolean tryPartition(int[] nums, int index, int sum) {
        if (index == nums.length - 1) {
            return sum == nums[index];
        }
        if (sum < 0) {
            return false;
        }
        if (memery[index][sum] != -1) {
            return memery[index][sum] == 1;
        }
        if (tryPartition(nums, index + 1, sum) ||
                tryPartition(nums, index + 1, sum - nums[index])){
            memery[index][sum] = 1;
            return true;
        } else {
            memery[index][sum] = 0;
            return false;
        }
    }
```

### 思路2
动态规划：要使得两个子集元素和相等，即为从数组中挑出部分元素组成集合A，使得sumA = SUM - sumA；也就是sumA = SUM / 2；这便是一个经典的0 - 1背包问题。假如SUM为奇数，直接返回false。

对于nums中数组，每个元素可选可不选，只要满足选出的元素和等于我们的目标值即可。建立dp数组，dp[i, sum] = dp[i - 1, sum] || dp[i - 1, sum - nums[i]];
因为这dp后一行结果只依赖前一行，我们可以只需要一维数组就够了，长度sum / 2 + 1;
```
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            //不能平分 说明不存在两个子集的元素和相等
            return false;
        }
        int target = sum / 2; //目标值 在n个数字中选出一定的数字，填满sum/2的背包
        boolean[] memery = new boolean[target + 1]; //一维数组
        //状态转移方程 dp[i, sum] = dp[i - 1, sum] || dp[i - 1, sum - nums[i]]; 后一行只依赖前一行 只需要一行就够了
        for(int i = 0; i <= target; i++) {
            if (i == 0 || i == nums[0]) {
                memery[i] = true; //和为0的时候 满足 什么都不选  //不为0 只有一个数字 必须等于 才符合
            }
        }
        //从选择第2个数字开始 因为第一个数字的dp数组已经初始化
        for(int i = 1; i < nums.length; i++) {
            //开始是否选择第i个数字
            for (int j = target; j >= nums[i]; j--) {
                //从大到小 才不能影响前面的结果
                memery[j] = memery[j] || memery[j - nums[i]]; //一行数组
            }
        }
        return memery[target];
    }
```
