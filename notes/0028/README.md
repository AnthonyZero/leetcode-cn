## 28.实现 strStr()

### 题目描述
实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例:
```
示例 1:

输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:

输入: haystack = "aaaaa", needle = "bba"
输出: -1
```

### 思路1
两个字符串，两个指针往后依次对比，当其中一个跳出循环结束时要么找到了（匹配串的指针先跳出），要么没找到（主串的指针先跳出）。如果在对比的过程中发生不相等情况，两个指针进行回退：主串指针回到开始位置下一个位置等待比较，匹配串回到开头位置。

时间复杂度：O(n*m) 
```   
    public int strStr(String haystack, String needle) {
        if (needle == "") {
            return 0;
        }
        int i = 0;
        int j = 0;
        char[] main = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        while (i < main.length && j < pattern.length) {
            if (main[i] == pattern[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1 ; //i回退 从下一个开始比较
                j = 0; //归0
            }
        }
        if (j == pattern.length) {
            //while完之后 如果j等于模式串长度 说明找到了
            return i - j;
        } else {
            return -1;
        }
    }
```

### 思路2
使用KMP算法（字符串模式匹配算法）：优化点相对于上面的暴力求解：如果主串中的一部分已经匹配好了模式串中的一部分，那么当下一个char不匹配的时候，就要从头来过，也就是模式串的第一个char的位置往右移动一格，然后对齐，重新比较。这样的话，对于主串中比较的那个指针来说，实际上有个回溯的过程。所谓的KMP算法是对暴力求解的一种改进。KMP的基本想法是，既然我们已经匹配成功了很多字符，那么即使后面的失配，前面得到的信息还是有用的，它的用途就是让我们在把模式串往右移动的时候可以避免错过匹配点地进行较大幅度的移动。（这个需要借助next数组 最大相同前后缀数的概念）

总结：尽量减少模式串与主串的匹配次数以达到快速匹配的目的。具体实现就是通过一个next()函数实现，函数本身包含了模式串的局部匹配信息，而且主串的指针不需要回退。
                                           
时间复杂度：O(n+m) 最坏的情况为O(m×n)
```
    public int strStr(String haystack, String needle) {
        if (needle == "") {
            return 0;
        }
        char[] main = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        int next[] = getNext(genPrefix(pattern)); //获取next数组
        int i = 0; //主串指针位置 不回退
        int j = 0; //模式串指针位置 不匹配的时候根据next数组 回退到响应位置
        while (i < main.length && j < pattern.length) {
            if (j == -1 || main[i] == pattern[j]) {
                //如果j根据next数组 回到next[0] 前面也就是-1 那么重置j = 0 i后移一位 继续比较
                j++;
                i++;
                //第二种 如果匹配 两个指针继续后移 比较
            } else {
                //不匹配 j根据得到的next数组 回退到指定位置
                j = next[j];
            }
        }
        if (j == pattern.length) {
            //while完之后 如果j等于模式串长度 说明找到了
            return i - j;
        } else {
            return -1;
        }
    }

    //获取模式串的前缀数组
    public int[] genPrefix(char[] pattern) {
        if (pattern.length == 0) {
            return new int[0];
        }
        int[] prefix = new int[pattern.length]; //结果 最大前缀数的数组
        int len = 0;
        int i = 1; //从1开始 求字符串的最大前后缀长度 并用prefix数组记录
        prefix[0] = 0; //毫无疑问 第一个p[0] = 0
        while(i < pattern.length) {
            if (pattern[len] == pattern[i]) {
                prefix[i] = len + 1; //后缀相等
                len++;
                i++;
            } else {
                if (len == 0) {
                    //不能再后退了 也就是当前i所在字符 不等于第一个字符
                    prefix[i] = len;
                    i++;
                } else {
                    // len回退到上一级最大前缀数 然后来继续和p[i]比较
                    len = prefix[len - 1];
                }
            }
        }
        return prefix;
    }

    //为了便于方便求解 把前缀数组右移一位 第一位补-1 得到next数组
    public int[] getNext(int[] prefix) {
        if (prefix.length == 0) {
            return new int[0];
        }
        int[] next = new int[prefix.length];
        next[0] = -1;
        for (int i = prefix.length-1; i > 0; i--) {
            next[i] = prefix[i - 1];
        }
        return next;
    }
```