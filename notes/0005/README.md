## 5.最长回文子串

### 题目描述
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1:
```
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
```

示例 2:
```
输入: "cbbd"
输出: "bb"
```

### 思路
“动态规划”: dp[i][j] 表示子串 s[i, j] 是否为回文子串。dp[i][j]为true的时候说明我们发现了一个回文子串，在递推的过程中不断更新最长回文子串
在这里i代表left,j代表right, 因为right肯定要大于等于left,所以dp二维数组 只需要推导一半结果即可。
> 如果一个字符串的头尾两个字符都不相等，那么这个字符串一定不是回文串；如果一个字符串的头尾两个字符相等，才有必要继续判断下去。
```   
    public String longestPalindrome(String s) {
        if(s == null || "".equals(s) || s.length() == 1) {
            return s;
        }

        boolean dp[][] = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = true; //一个字符也算是回文
        }

        int maxLength = 1; //最大回文长度
        int start = 0; //ac 两个字符情况下 至少取一个字符
        for(int j = 1; j < s.length(); j++) {
            for(int i = 0; i < j; i++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2) {
                        //aba情况 直接为true
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                } else {
                    dp[i][j] = false;
                }

                //有新的回文子串
                if(dp[i][j] && maxLength < j - i + 1) {
                    //更新最长回文子串
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
```