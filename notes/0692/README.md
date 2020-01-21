## 692. 前K个高频单词

### 题目描述
给一非空的单词列表，返回前 k 个出现次数最多的单词。

返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。

示例1:
```
输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
输出: ["i", "love"]
解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 "i" 在 "love" 之前。
```

示例2:
```
输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
输出: ["the", "is", "sunny", "day"]
解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。

```

注意：
1. 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
2. 输入的单词均由小写字母组成。

### 思路
使用堆结构（优先队列）+ 哈希表：计算每个单词的频率，然后将其添加到存储到大小为 k 的小根堆中。它将频率最小的候选项放在堆的顶部。最后，我们从堆中弹出最多 k 次，并反转结果，就可以得到前 k 个高频单词。
```   
    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        PriorityQueue<String> queue = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (map.get(o1) != map.get(o2)) {
                    return map.get(o1) - map.get(o2);  //频率低的在队头
                } else {
                    return o2.compareTo(o1);
                }
            }
        });
        for (String word : map.keySet()) {
            queue.add(word);
            if (queue.size() > k) {  //超过了k个的话 频率低的出队
                queue.remove();
            }
        }

        List<String> topK = new ArrayList<>();
        while(!queue.isEmpty()) {
            topK.add(queue.remove());
        }
        Collections.reverse(topK);
        return topK;
    }
```

复杂度分析：

* 时间复杂度：O(Nlogk)。其中 N 是 words 的长度。我们用 O(N) 的时间计算每个单词的频率，然后将 N 个单词添加到堆中，添加每个单词的时间为 O(logk) 。最后，我们从堆中弹出最多 k 次。因为 k ≤ N 的值，总共是O(Nlogk)。
* 空间复杂度：O(N)，用于存储我们计数的空间

