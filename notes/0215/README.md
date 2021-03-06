## 215.数组中的第K个最大元素

### 题目描述
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:
```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```

示例 2:
```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```
说明
你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

### 思路
初始一个最小堆中始终保存k个元素. 遍历数组, 每次将元素加入堆中, 如果加入之后堆中元素个数超过k个, 堆顶元素出堆.变相的把比目标值较小的元素剔除。留下了前k大的元素，从小到大（堆顶是目标值）

例如: [3,2,1,5,6,4] 和 k=2

3入堆, 堆=[3]

2入堆, 堆=[2,3]

1入堆, 堆=[1,2,3] 超过2个. 1出堆, 堆=[2,3]

5入堆, 堆=[2,3,5] 超过2个. 2出堆, 堆=[3,5]

6入堆, 堆=[3,5,6] 超过2个. 3出堆, 堆=[5,6]

4入堆, 堆=[4,5,6] 超过2个. 4出堆, 堆=[5,6]

结果堆顶是5

```   
    public int findKthLargest(int[] nums, int k) {
        // 最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int num : nums) {
            //每次都入队 数字较小的在队头
            queue.add(num);
            if (queue.size() > k) {
                //当超过了k个容量 较小值出队
                queue.remove();
            }
        }
        //最后维护了一个k 容量的最小堆  堆顶是前k个最大的元素
       return queue.peek();
    }
```
