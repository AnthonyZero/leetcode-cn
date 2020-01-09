## 303.区域和检索 - 数组不可变

### 题目描述
给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

示例 :
```
给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
```
说明
1. 你可以假设数组不可变。
2. 会多次调用 sumRange 方法。

### 思路
因为要多次查询，所以保存一个前缀和数组

我们用一个数组记录前几个元素的和，那么sum(i, j) = data[j + 1] - data[i]

为了方便计算加个0 -》data[0] = 0  比如sum(0,0)  = data[1] - data[0]
```   
    class NumArray {
        private int[] data;  //保存前n个数之和
        public NumArray(int[] nums) {
            data = new int[nums.length + 1];
            data[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                data[i + 1] = data[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return data[j + 1] - data[i]; //data(6) - data(0) 就是（0,5）这个区间的和
        }
    }
```

时间复杂度：每次查询的时间 O(1)，O(N)预计算时间。由于累积和被缓存，每个sumrange查询都可以用 O(1)时间计算。
空间复杂度：O(n).

