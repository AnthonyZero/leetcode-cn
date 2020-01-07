## 451.根据字符出现频率排序

### 题目描述
给定一个字符串，请将字符串里的字符按照出现的频率降序排列

示例 1:
```
输入:
"tree"

输出:
"eert"

解释:
'e'出现两次，'r'和't'都只出现一次。
因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。

```

示例 2:
```
输入:
"cccaaa"

输出:
"cccaaa"

解释:
'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
注意"cacaca"是不正确的，因为相同的字母必须放在一起。

```

示例 3:
```
输入:
"Aabb"

输出:
"bbAa"

解释:
此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
注意'A'和'a'被认为是两种不同的字符。

```

### 思路
1. 使用HashMap存储每个元素的出现次数
2. 使用PriorityQueue队列自定义排序规则(频率高的在队头也就是堆顶),对HashMap中的每个Key元素入队列
3. 使用StringBuilder完成字符拼接

```   
    public String frequencySort(String s) {
        if ("".equals(s) || s == null) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>(); //每个字符出现的频率
        for (int i = 0; i < s.length(); i++) {
            Character charAt = s.charAt(i);
            if (map.containsKey(charAt)) {
                map.put(charAt, map.get(charAt) + 1);
            } else {
                map.put(charAt, 1);
            }
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {  //频率大的在队列头
                return map.get(o2) - map.get(o1);
            }
        });
        for(Character character : map.keySet()) {
            queue.add(character);
        }

        StringBuilder sb = new StringBuilder("");
        while(!queue.isEmpty()) {
            Character character = queue.remove(); //出队
            for (int i = 0; i < map.get(character); i++) { //根据频率append
                sb.append(character);
            }
        }
        return sb.toString();
    }
```
