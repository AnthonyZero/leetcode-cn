## 17.电话号码的字母组合

### 题目描述
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

![图](https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png)

```
示例 :

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

说明:

尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序

### 思路
回溯是一种通过穷举所有可能情况来找到所有解的算法

```   
    private String letterMap[] = {
                "abc",  //2
                "def",  //3
                "ghi",  //4
                "jkl",  //5
                "mno",  //6
                "pqrs", //7
                "tuv",  //8
                "wxyz"  //9
    };
    private List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) {
            return new ArrayList<>();
        }
        findLetterPath(digits, 0, "");
        return res;
    }

    // 按照每个数字递归下去 寻找每一步的路径。 prefix是digits[index-1] 的结果
    private void findLetterPath(String digits, int index, String prefix) {
        if (index == digits.length()) {
            //所有数字已经处理完
            res.add(prefix);
            return;
        }
        char c = digits.charAt(index); //数字 2~9
        String letter = letterMap[c - '2'];
        for(int i = 0; i < letter.length(); i++) {
            findLetterPath(digits, index + 1, prefix + letter.charAt(i));
        }
        return;
    }
```

