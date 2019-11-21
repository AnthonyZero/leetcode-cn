## 14.最长公共前缀

### 题目描述
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

```
示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
```

### 思路
暂定第一个字符串是最长公共前缀（prefix）,然后遍历数组的每个元素（从1开始）。依次比较prefix的每个字符和元素的每个字符。遇到不相等的，或者已经循环到了prefix的末尾了（防止越界）。跳出循环更新当前的prefix继续下次循环比较。在每次循环更新prefix之前判断：如果前面的比较结果 第一个字符都不相等那么肯定返回空字符串。

```   
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String targetPrefix = strs[0]; //暂时第一个字符串为最长公共前缀
        for (int i = 1; i < strs.length; i++) {
            String compare = strs[i];
            int j = 0;
            for (;j < compare.length(); j++) { //两个字符串 按个比较每个字符 遇到不相等 更新targetPrefix
                if (j >= targetPrefix.length() || targetPrefix.charAt(j) != compare.charAt(j)) {
                    break;
                }
            }
            if (j == 0) { //第一个字符都不匹配
                return "";
            }
            targetPrefix = compare.substring(0, j);
        }

        return targetPrefix;
    }
```

