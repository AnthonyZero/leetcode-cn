## 35. 搜索插入位置

### 题目描述
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

```
示例 1:
输入: [1,3,5,6], 5
输出: 2

示例 2:
输入: [1,3,5,6], 2
输出: 1

示例 3:
输入: [1,3,5,6], 7
输出: 4

示例 4:
输入: [1,3,5,6], 0
输出: 0

```



### 思路
这是一道考察二分搜索的简单题，如果暴力求解需要 O(n)的时间复杂度，但是如果二分的话则可以降低到 O(logn)的时间复杂度。

二分查找思路：先设定左侧下标 left 和右侧下标 right，再计算中间下标 mid。每次根据 nums[mid] 和 target 之间的大小进行判断，相等则直接返回下标， target > nums[mid] 则 left 右移（mid + 1），target < nums[mid] 则 right 左移(mid - 1)

查找结束如果没有相等值则返回 left，该值为插入位置。

复杂度分析

时间复杂度：O(logn)
```   
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int middle = (left + right) / 2;
            if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return left;
    }
```

