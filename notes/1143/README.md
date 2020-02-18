## 1143. 最长公共子序列

### 题目描述
给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

若这两个字符串没有公共子序列，则返回 0。

示例1：
```
输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace"，它的长度为 3。
```

示例2：
```
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc"，它的长度为 3。
```

示例3：
```
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0。
```

提示：
* 1 <= text1.length <= 1000
* 1 <= text2.length <= 1000
* 输入的字符串只含有小写英文字符。


### 思路1
自顶向下记忆化搜索（递归方式），求一个大的问题转化为较小问题的最优解：
text1和text2的最长公共子序列如果两个字符串最后一位是相同的，那么结果就是text1(m-1)字符串和
text2(n-1)字符串的最长公共子序列长度+1；

如果不相同，那么两个字符串分别错开最后一位的，分别求最长公共子序列长度的最大值即是最终结果
```   
    private int[][] memery;
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || "".equals(text1) || text2 == null || "".equals(text2)) {
            return 0;
        }
        memery = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            Arrays.fill(memery[i], -1);
        }
        return longest(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    private int longest(String text1, String text2, int index1, int index2) {
        if (index1 < 0 || index2 < 0) {
            return 0;
        }
        if(memery[index1][index2] != -1) {
            return memery[index1][index2];
        }
        if (text1.charAt(index1) == text2.charAt(index2)) {
            //最后两个字符相同
            memery[index1][index2] = longest(text1, text2, index1 - 1, index2 - 1) + 1;
        } else {
            memery[index1][index2] = Math.max(
                    longest(text1, text2, index1 - 1, index2),
                    longest(text1, text2, index1, index2 - 1));
        }
        return memery[index1][index2];
    }   
```

### 思路2
动态规划(迭代方式):lcs[i][j]依赖于lcs[i-1][j-1]和lcs[i-1][j],lcs[i][j-1]的结果，先初始化第一行第一列然后迭代求解。
```
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || "".equals(text1) || text2 == null || "".equals(text2)) {
            return 0;
        }
        int[][] lcs = new int[text1.length()][text2.length()];
        //初始化第一行第一列
        //只要存在有一个字符相同，那么这个字符后面的lcs结果就是1
        int temp = 0;
        for(int i = 0; i < text1.length(); i++) {
            //text1每个字符和text2第一个字符比较，text1遇到相同的时候，这后面的lcs结果都应该是1，因为此时text2只要一个字符，lcs结果最长也只是1
            if(text1.charAt(i) == text2.charAt(0)) {
                temp = 1;
            }
            lcs[i][0] = temp;
        }

        temp = 0;
        for(int i = 0; i < text2.length(); i++) {
            if (text2.charAt(i) == text1.charAt(0)) {
                temp = 1;
            }
            lcs[0][i] = temp;
        }

        //迭代过程
        for(int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Integer.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        return lcs[text1.length() - 1][text2.length() - 1];
    }
```

