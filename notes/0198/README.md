## 198. 打家劫舍

### 题目描述
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例1:
```
输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
```

示例2:
```
输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
```
### 思路1
递归：自顶向下
```   
    private Map<Integer,Integer> memery = new HashMap<>();
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return robChoose(nums, 0);
    }

    public int robChoose(int[] nums, int index) {
        if (index == nums.length - 1) {
            return nums[index];
        }
        if (index == nums.length - 2) {
            return Math.max(nums[index], nums[index + 1]);
        }
        if (memery.containsKey(index)) {
            return memery.get(index);
        }
        int total = Math.max(nums[index] + robChoose(nums, index + 2),
                robChoose(nums, index + 1));
        memery.put(index, total);
        return total;
    }
```

### 思路2
动态规划方程：dp[n] = MAX( dp[n-1], dp[n-2] + num[n]),

由于不可以在相邻的房屋闯入，所以在当前位置 n 房屋可盗窃的最大值，要么就是 n-1 房屋可盗窃的最大值，要么就是 n-2 房屋可盗窃的最大值加上当前房屋的值，二者之间取最大值

时间复杂度：O(n)，n为数组长度
```
   public int rob(int[] nums) {
       if (nums.length == 0) {
           return 0;
       }
       if (nums.length == 1) {
           return nums[0];
       }
       int[] dp = new int[nums.length]; //dp[i] 代表考虑偷取0到i房屋的最高金额
       dp[0] = nums[0];
       dp[1] = Math.max(nums[0], nums[1]);
       for (int i = 2; i < nums.length; i++) {
           //是否选择偷取第i个房屋
           dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
       }
       return dp[nums.length - 1];
   }
```