## 3.无重复字符的最长子串

### 题目描述
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例:
```
示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

### 思路1
利用Hash表存储Key-字符,Value-字符所在位置。指定一个指针从1开始往后遍历，并不断把还没有出现重复的字符添加到Map中去。遇到字符已经存在于Map中后，把指针回退到上一次该字符出现的位置（通过我们的Map得到）+1 重新开始往下找最长子串。直到指针到目标字符串最后位置退出循环，比较我们暂存的最长子串最大值和Map的长度，取两者最大的一个就是我们的最长子串 的长度
```   
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int maxLength = 1;
        int i = 1;
        Map<Character,Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        while(i < s.length()) {
            if (map.keySet().contains(s.charAt(i))) {
                //已存在
                if (map.size() > maxLength) { //最长子串 有新值
                    maxLength = map.size();
                }
                // 拿到上一次出现这个字符的位置 从下一个字符开始
                i = map.get(s.charAt(i)) + 1;
                map.clear();
                map.put(s.charAt(i), i); //重新计数
            } else {
                map.put(s.charAt(i), i);
            }
            i++;
        }
        return Integer.max(maxLength, map.size());
    }
```

### 思路2（滑动窗口）
上面的思路时间耗时较长，是因为指针遇到map已存在的情况下，会回退指针到上一次出现这个字符的位置的下一个字符又重新开始。

针对这种情况可以优化：保证指针不回退（多引入一个left指针，遇到重复的时候就右移位置，此时循环的索引i到left之间的距离就是我们需要的字串的长度），当然每次比较都要取最大值。时间复杂度就降低到：O(n)
```
     public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.keySet().contains(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1); //移动left指针
            }
            map.put(s.charAt(i), i); //保存字符的位置
            maxLength = Math.max(maxLength, i - left + 1); //每次移动都更新当前子串最大长度 两个指针之间的距离（字串长度大小）
        }
        return maxLength;
    }
```