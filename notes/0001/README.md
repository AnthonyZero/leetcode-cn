## 1.两数之和

### 题目描述
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素

示例:
```
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```

### 思路1
暴力法，双循环求和，时间复杂度为O(n^2) 空间复杂度为O(1) 不推荐
```   
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i< nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
```

### 思路2
用空间换时间，利用hashmap存储键（对应数组元素值），值（对应该元素值所在的数组下标）对
。比如2就是 （2,0） 7就是（7，1）。在一次循环的时候初始化这个map的时候，顺便检查target-元素值 是否已经在map的key元素中，在的话返回结果（target-元素值所存储的索引值以及当前循环下标），不在的话将当前值陆续put进map中

时间复杂度：O(n) 哈希表的查找时间是 O(1)

空间复杂度：O(n)（取决于哈希表中存储的元素数量）
```
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (map.containsKey(value) && map.get(value) != i) {
                return new int[]{map.get(value), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
```