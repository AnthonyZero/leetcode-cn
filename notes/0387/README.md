## 387.字符串中的第一个唯一字符

### 题目描述
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

```
s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.
```

注意事项：您可以假定该字符串只包含小写字母。

### 思路
只包含小写字母，那么就直接用数组来表示每个字母的频率，遍历第二遍字符串之后，只要第一个字母的频率为1就是我们需要的结果。

```   
    public int firstUniqChar(String s) {
        if(s == null || "".equals(s)) {
            return -1;
        }
        int[] freq = new int[26]; //每个字母 的频率 0是代表a 依次类推
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq[c - 'a'] = freq[c - 'a'] + 1;
        }
        for (int i = 0; i < s.length(); i++) {
            //挨个看每个字母 的频率是否为1（不重复 第一个就返回了）
            if(freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
```
