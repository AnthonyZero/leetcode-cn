## 125.验证回文串

### 题目描述
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例:
```
示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true

示例 2:

输入: "race a car"
输出: false
```

### 思路
考察双指针问题，左指针不断向右边移动，右指针不断向左边移动，如果遇到不是字母或者数字，跳过。在判断的过程中进行比较如果字符不相等就跳出循环得出结论不是回文字符串。循环结束的条件是左指针所在位置小于右指针所在位置。
```   
    public boolean isPalindrome(String s) {
        if (s.equals("")) {
            return true;
        }
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        boolean result = true;
        while (i < j) {
            if (!(Character.isAlphabetic(chars[i]) || Character.isDigit(chars[i]))) {
                i++;
                continue;
            }
            if (!(Character.isAlphabetic(chars[j]) || Character.isDigit(chars[j]))) {
                j--;
                continue;
            }
            if (Character.toUpperCase(chars[i]) != Character.toUpperCase(chars[j])) {
                result = false;
                break;
            } else {
                i++;
                j--;
            }
        }
        return result;
    }
```
