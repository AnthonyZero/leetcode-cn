## 67.二进制求和

### 题目描述
给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 非空 字符串且只包含数字 1 和 0。

示例1:
```
输入: a = "11", b = "1"
输出: "100"
```
示例2:
```
输入: a = "1010", b = "1011"
输出: "10101"
```

### 思路: 末尾对齐，逐位相加
从末尾进行遍历计算，进行以此加法（进位标示也加上）。在进行计算时直接拼接字符串，最后会得到一个反向字符，需要再进行翻转返回结果

> 在十进制的计算中「逢十进一」，二进制中我们需要「逢二进一」。
```   
    public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer("");
        int ca = 0; //是否有进位
        //两个字符串 从后往前遍历
        for(int i = a.length() - 1, j = b.length() - 1; i >=0 || j>=0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0; //在a字符串未越界前提下 sum加此刻元素值 1或者0
            sum += j >= 0 ? b.charAt(j) - '0' : 0; //在b字符串未越界前提下 sum加此刻元素值 1或者0
            sb.append(sum % 2);
            ca = sum / 2;
        }
        if(ca == 1) {
            sb.append("1");
        }
        //字符串反转
        return sb.reverse().toString();
    }
```