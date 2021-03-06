## 26. 删除排序数组中的重复项

### 题目描述
给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

示例 1:

```
给定数组 nums = [1,1,2], 

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 

你不需要考虑数组中超出新长度后面的元素。
```

示例 2:

```
给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

你不需要考虑数组中超出新长度后面的元素。
```

### 思路
两个指针，一个指向待修改的位置，一个用来探索待修改的位置 将来被修改的值的位置在哪里。初始compare为已经存在的数，目标两个指针都从1开始，探索指针j不断后移，要找与compare不相等的数来修改i的数值。找到了就替换i所在位置的值，i同时右移一位，compare同时更新。

执行用时 :1 ms, 在所有 java 提交中击败了100.00%的用户

内存消耗 :39.7 MB, 在所有 java 提交中击败了95.66%的用户

复杂度分析

时间复杂度：O(n)，假设数组的长度是 n，那么 i 和 j 分别最多遍历 n 步。

空间复杂度：O(1)。
```   
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 1; //待修改的指针
        int j = 1; //查找目标值（替换值）的指针
        int compapre = nums[0];
        while(j < nums.length) {
            if (nums[j] != compapre) {
                //不相等 替换
                nums[i] = nums[j];
                compapre = nums[i];
                i++;
            }
            j++;
        }
        return i;
    }
```

