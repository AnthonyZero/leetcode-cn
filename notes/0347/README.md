## 347.前 K 个高频元素

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
1. 借助 哈希表 来建立数字和其出现次数的映射，遍历一遍数组统计元素的频率
2. 维护一个元素数目为 k 的最小堆（这里是使用优先队列 底层是最小堆）
3. 在堆元素数量还没到达k的时候，直接入队，后面达到k之后每次都将新的元素与堆顶元素（堆中频率最小的元素）进行比较
4. 如果新的元素的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中（维持堆容量为k）
5. 最终，堆中的 k 个元素即为前 k 个高频元素

```   
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>(); //每个元素 -》 元素频率
        for(int num: nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 优先队列(默认是最小堆) 只允许存最多k个元素 （我们要的结果：这k个元素是 所有元素中频率排前的）
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) { //指定元素频率的比较
                return map.get(o1) - map.get(o2);  //频率越小 越在队头
            }
        });
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                //队列还没到指定容量 直接入队
                queue.add(entry.getKey());
            } else if (entry.getValue() > map.get(queue.peek())) {
                // 后面的元素的频率 如果大于队头元素频率  则出队队头 进行替换
                queue.remove();
                queue.add(entry.getKey());
            }
        }

        List<Integer> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            list.add(queue.remove());
        }
        return list;
    }
```
