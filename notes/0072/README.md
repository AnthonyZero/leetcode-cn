## 72.编辑距离

### 题目描述
给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

    插入一个字符
    删除一个字符
    替换一个字符


示例 1:
```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```

示例 2:
```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```

### 思路1
递归自顶向下：记忆化
```   
    private int[][] memery;
    public int minDistance(String word1, String word2) {
        memery = new int[word1.length()][word2.length()];
        for(int i = 0; i < word1.length(); i++) {
            Arrays.fill(memery[i], -1);
        }
        return minDistance(word1, word1.length() - 1, word2, word2.length() - 1);
    }

    // 求word1[0,index1] 和 word2[0,index2]的编辑距离 从字符串末尾递归到前面
    // word1 变成word2 word1[index1] 可以有删除 替换 新增操作
    public int minDistance(String word1, int index1, String word2, int index2) {
        // 是 index1 走完 word1 或 index2 走完 word2，可以直接返回另一个字符串剩下的长度
        if(index1 < 0) {
            return index2 + 1;
        }
        if (index2 < 0) {
            return index1 + 1;
        }
        if (memery[index1][index2] != -1) {
            return memery[index1][index2];
        }
        //比较
        if(word1.charAt(index1) == word2.charAt(index2)) {
            //这两个字符相同 两个指针各自移动一位 操作不用+1 本来两个字符就相同
            memery[index1][index2] = minDistance(word1, index1 - 1, word2, index2 - 1);
        } else {
            //增 删 替换 3个操作 哪个编辑最小选择哪个
            memery[index1][index2] = min(
                     //word1插入一个字符指针不动 word2前移一位
                    minDistance(word1, index1, word2, index2 - 1) + 1,
                     //word1删除一个字符并前移一位 word2不变
                    minDistance(word1, index1 - 1, word2, index2) + 1,
                    //word1替换一个字符 两个指针都前移一位
                    minDistance(word1, index1 - 1, word2, index2 - 1) + 1
            );
        }
        return memery[index1][index2];
    }

    //三数最小值
    public int min(int i, int j, int k) {
        return Integer.min(i, Integer.min(j, k));
    }
```

### 思路2
动态规划：迭代方式

临界条件（从字符串末尾到首部 两个指针移动）是 i 走完 word1 或 j 走完 word2，可以直接返回另一个字符串剩下的长度。这个长度是需要加上的（字符串变化需要的）
```
if s1[i] == s2[j]:
    啥都别做（skip）
    i, j 同时向前移动
else:
    三选一 哪个操作最后得到的编辑距离最小，就选谁：
        插入（insert）
        删除（delete）
        替换（replace）
```

dp[i][j] 返回 word1[0..i] 和 word2[0..j] 的最小编辑距离
```
   // 动态规划求解
   public int minDistance(String word1, String word2) {
       int m = word1.length();
       int n = word2.length();
       int dp[][] = new int[m + 1][n + 1]; //第1行 第1列 作为临界条件
       // 临界情况 如果其中一个字符串已经遍历完 返回另一个字符串剩余的字符个数
       for(int i = 0; i <= m; i++) {
           dp[i][0] = i;
       }
       for (int j = 0; j <= n; j ++) {
           dp[0][j] = j;
       }
       //字符串索引从1开始 dp[i][j] 依赖于左边 上面 斜左上 3个元素
       for(int i = 1; i <= m; i++) {
           for(int j = 1; j <= n; j++) {
               //这里要注意 是因为i - 1字符 跟j -1 字符相同  才能推出dp[i][j] = dp[i - 1][j - 1]
               //（这迭代）跟上面递归的思想相反
               if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                   dp[i][j] = dp[i - 1][j - 1]; //不做任何操作即可
               } else {
                   dp[i][j] = min(
                           dp[i - 1][j] + 1, //word1删除一个字符
                           dp[i][j - 1] + 1,    //word1新增一个字符
                           dp[i-1][j-1] + 1   //word1 替换操作
                   );
               }
           }
       }
       return dp[m][n];
   }

   //三数最小值
   public int min(int i, int j, int k) {
       return Integer.min(i, Integer.min(j, k));
   }
```

复杂度分析
* 时间复杂度 ：O(mn)，其中 m 为 word1 的长度，n 为 word2 的长度。
* 空间复杂度 ：O(mn)，我们需要大小为 O(mn) 的 dp 数组来记录状态值。