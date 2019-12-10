## 349.两个数组的交集

### 题目描述
给定两个数组，编写一个函数来计算它们的交集。

```
示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2]
```

```
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [9,4]
```
说明
* 输出结果中的每个元素一定是唯一的。
* 我们可以不考虑输出结果的顺序。

### 思路
利用Set去重的特点，遍历第一个数组存入hash集合中。遍历第二个数组的时候查看是否包含此元素，包含那么就是交集结果的其中一个元素，因为存在第二个数组存在重复元素，所以遍历的时候如果包含的时候同步删除集合中此元素。

```   
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                list.add(nums2[i]);
                set.remove(nums2[i]);
            }
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
```
