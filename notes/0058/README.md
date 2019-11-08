## 58.最后一个单词的长度

### 题目描述
给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。

如果不存在最后一个单词，请返回 0 。

说明：一个单词是指由字母组成，但不包含任何空格的字符串。

示例:
```
输入: "Hello World"
输出: 5
```

### 思路1
既然求最后一个单词的长度，那么通过空格字符串分割拿到字符串数组，拿到最后一个字符串即可
```   
    public int lengthOfLastWord(String s) {
            String[] words = s.split(" ");
            if (words == null || words.length == 0) {
                return 0;
            } else {
                return words[words.length - 1].length();
            }
    }
```

### 思路2
考虑到字符串首尾有空格，通过trim()去掉之后从后向前找到第一个空格，那么这个空格后的字符串就是我们要找的结果。

耗费 0 ms ， 同JAVA 击败100%

时间复杂度：O(n) 
```
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int index = s.lastIndexOf(" ") + 1;
        return s.substring(index).length();
    }
```