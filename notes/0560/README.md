## 560.和为K的子数组

### 题目描述
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例:
```
输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
```

说明 :
1. 数组的长度为 [1, 20,000]。
2. 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。


### 思路
利用前缀和数组来求解：map辅助记录前缀和 元素的和出现的次数

前缀和代表0到任意数字i之间的元素和，比如0到i代表num[0]到nums[i]这之间所有的数加起来的和。

如果[0,j]的范围和 - [0,i]的范围和(j >= i) 等于目标值，那么[i,j]这个范围的子数组
就是符合要求题意的连续子数组。 因为连续子数组减去连续子数组，必定也还是连续子数组

用Map来保存和出现的次数，初始化的时候map.put(0, 1),如果第一个元素就是k, 那么nums[0] - k就等于0, 就应该从map中取出来1 而不能初始化为0。
为什么每次都是res += map.get(sum - k) 而不是res++; 考虑i,j,k (i < j < k), 如果[0,i]元素和 等于[0,j]的元素和（tmpSum），
那么map里就应该保存该元素和tmpSum出现次数为2,而不是1。 因为假设[0,k] - 目标值 = tmpSum的时候，符合要求的就应该有两个：
一个是[i,k],另一个是[j,k], 所以不能简单的进行++，这就是这使用map的原因。
```   
    public int subarraySum(int[] nums, int k) {
        //连续子数组和 的次数
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, 1); //出现和为0的 一次
        int sum = 0;
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];  //[0,i]的和
            if(map.containsKey(sum - k)) { //表示连续子数组减去连续子数组，必定为连续子数组
                //找到了 有连续子数组和 = k
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
```

