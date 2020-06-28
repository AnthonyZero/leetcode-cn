## 1456.定长子串中元音的最大数目

### 题目描述
给你字符串 s 和整数 k 。

请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。

英文中的 元音字母 为（a, e, i, o, u）。

示例1
```

输入：s = "abciiidef", k = 3
输出：3
解释：子字符串 "iii" 包含 3 个元音字母。
```
示例2
```

输入：s = "aeiou", k = 2
输出：2
解释：任意长度为 2 的子字符串都包含 2 个元音字母。
```
示例3
```

输入：s = "leetcode", k = 3
输出：2
解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
```
示例4
```

输入：s = "rhythms", k = 4
输出：0
解释：字符串 s 中不含任何元音字母。
```
示例5
```

输入：s = "tryhard", k = 4
输出：1
```


提示：
* 1 <= s.length <= 10^5
* s 由小写英文字母组成
* 1 <= k <= s.length


### 思路
滑动窗口：本题的整数k相当于滑动的窗口的大小是固定的。那么就简单了，在窗口不断右移的过程中，不断比较当前窗口
的元音字母数和当前最大值即可。

举例说明：例如leetcode （k = 3）

1. 最开始右指针不断右移看 l; le; lee 字串。（此时左指针不变）
2. 窗口大小已经满足了k, 维持不变（此时左右指针 同时不断右移即可）此时看eet, etc, tco等等
3. 在左右指针右移的过程中，边界字符发生了变化 需要纠正。 比如eet -> etc (左边界失去了一个元音,右边界无新增元音 此时窗口的元音数减少了1)
4. 每次右指针移动的时候 都需要更新最大元音字母数 这个最终结果。
```   
   public int maxVowels(String s, int k) {
       char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
       Set<Character> set = new HashSet<>();
       for(char c : vowels) {
           set.add(c);
       }
       int maxLength = 0;//最大元音字母数
       int left = 0; //左指针
       int i = 0; //右指针
       int current = 0; //当前窗口的元音字母数
       while(i < s.length()) {
           if(i - left < k) {
               //最开始窗口大小 还未到规定大小k 遇到是元音字母 就直接++
               if(set.contains(s.charAt(i))) {
                   current++;
               }
               i++;
           } else {
               left++; //窗口右移一位（i已经加了1了 left同步移动一位）
               //(i - left + 1 = k) 现在两个指针之间的间隔字母数量 是等于k的
               // 维持窗口大小不变 left i不断像右移 一位
               if(set.contains(s.charAt(left - 1))) {
                   current--; //当前窗口失去了前一个字符 如果是元音字母 就该减1
               }
               if(set.contains(s.charAt(i))) {
                   current++; //当前窗口包含了新的一个字符
               }
               i++;
           }
           maxLength = Integer.max(maxLength, current);
       }
       return maxLength;
   }
```   

