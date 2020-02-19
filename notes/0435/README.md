## 435.无重叠区间

### 题目描述
给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

注意:

* 可以认为区间的终点总是大于它的起点。
* 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。

示例 1:
```   
输入: [ [1,2], [2,3], [3,4], [1,3] ]

输出: 1

解释: 移除 [1,3] 后，剩下的区间没有重叠。

```

示例 2:   
```
输入: [ [1,2], [1,2], [1,2] ]

输出: 2

解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
```

示例 3:   
```
输入: [ [1,2], [2,3] ]

输出: 0

解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
```

### 思路
贪心算法：只要算法这些区间中最多有几个互不相交的区间，然后区间总数一减就是我们需要最少需要移除区间的数量。

将每个区间的 end 数值升序排序,选择第一个区间pre，从第二个区间开始，只要区间start>=前一个区间的end,说明这两个区间
不相交，结果+1。同时更新pre,继续找下个符合的区间。
```   
    //第0列是start 第1列是end
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 按 end 升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1]; //按照end 升序排列
            }
        });
        // 至少有一个区间不相交
        int res = 1;
        // 第一个区间已使用
        int pre = 0;
        //从第二个区间开始
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            int start = interval[0]; //当前区间start
            if (start >= intervals[pre][1]) { //大于等于前一个区间end
                // 找到一个合适的区间 继续寻找下一个选择的区间
                res++;
                pre = i; //更新pre
            }
        }
        //总区间数 - 互不相交的区间数 = 需要移除的数
        return intervals.length - res;
    }
```
