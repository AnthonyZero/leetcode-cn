## 392.判断子序列

### 题目描述
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例 1:
```
s = "abc", t = "ahbgdc"

返回 true.
```

示例 2:
```
s = "axc", t = "ahbgdc"

返回 false.
```

后续挑战 :

如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

### 思路
双指针法：初始化s字串和t字串的指针为i,j为第一个位置。j不断往后移动，直到t字符串遍历完成。当两个指针代表的字符相同匹配的时候，i也同步后移一步。
t字符串遍历完成之后，看i指针是否还在s字串范围内，如果还在说明s所有的字符没有完全找到在t字符串中的位置，返回false不是子序列。如果指针已经大于等于s
字符串的长度了，返回true代表是子序列。

> 在j指针不断后移的时候，要判断s字串是否已经匹配完成（防止i指针越界,比如"abc"和"abcddgdc"）
```   
     public boolean isSubsequence(String s, String t) {
        if ("".equals(s)) {
            return true;
        }
        int i = 0;
        int j = 0;
        while(j < t.length()) {
            if (i == s.length()) {
                return true;
            }
            if(s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        if (i == s.length()) {
            return true;
        } else {
            return false;
        }
    }
```
