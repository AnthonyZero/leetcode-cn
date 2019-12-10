## 350.两个数组的交集 II

### 题目描述
给定两个数组，编写一个函数来计算它们的交集。

```
示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
```

```
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
```
说明
* 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
* 我们可以不考虑输出结果的顺序。

### 思路
利用Map存储第一个数组的元素Key及出现的次数Value。遍历第二个数组的时候，如果Map中包含的话添加到我们的结果中，并且次数减一，当减到0的时候主动删除Map中此元素。

```   
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else {
                map.put(nums1[i], 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                list.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
                if (map.get(nums2[i]) == 0) {
                    map.remove(nums2[i]);
                }
            }
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
```
