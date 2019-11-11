## 20.有效的括号

### 题目描述
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

注意空字符串可被认为是有效字符串。

```
示例 1:

输入: "()"
输出: true

示例 2:

输入: "()[]{}"
输出: true

示例 3:

输入: "(]"
输出: false

示例 4:

输入: "([)]"
输出: false

示例 5:

输入: "{[]}"
输出: true
```

### 思路
利用栈先进先出的特点求解。循环字符数组，如果栈顶的元素是开括号并且匹配当前字符（闭括号），就直接栈顶元素出栈。否则把当前元素压栈。 循环完毕如果栈是空的就是有效字符串。在判断的时候优化有二：
一是字符串长度是奇数 肯定不是无效字符串，二是在操作栈的时候 如果栈的深度 已经大于了 剩下的需要处理的字符数 就不是无效字符串。

复杂度分析
* 时间复杂度：O(n)，一次遍历给定的字符串中的一个字符并在栈上进行 O(1) 的推入和弹出操作。
* 空间复杂度：O(n)，哈希表和栈使用线性的空间大小
```   
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')');
    }};

    public boolean isValid(String s) {
        if ("".equals(s)) {
            return true;
        }
        if (s.length() % 2 == 1) { //如果字符串长度是奇数 肯定不是无效字符串
            return false;
        }
        char[] target = s.toCharArray();
        Stack stack = new Stack();
        for (int i = 0; i < target.length; i++) {
            if (stack.isEmpty()) {
                stack.push(target[i]);
            } else {
                if (stack.size() > (s.length() - i)) { //如果栈的深度 已经大于了 剩下的需要处理的字符数
                    return false;
                }
                if (map.keySet().contains(stack.peek()) && map.get(stack.peek()).equals(target[i])) {
                    stack.pop();
                } else {
                    stack.push(target[i]);
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
```

