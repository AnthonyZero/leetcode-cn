## 633.平方数之和

### 题目描述
给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。

示例 1:
```
输入: 5
输出: True
解释: 1 * 1 + 2 * 2 = 5
```

示例 2:
```
输入: 3
输出: False
```

### 思路1
首先枚举 i，并保证 i^2 <= c，随后我们通过二分查找的方法找出是否存在 j，满足 j^2 = c - i^2。二分查找的范围为 [0, c - i^2]。
```
    public boolean judgeSquareSum(int c) {
        for(long i = 0; i * i <= c; i++) {
            int j = c - (int)(i * i); //差值
            if(search(0, j, j)) {
                return true;
            }
        }
        return false;
    }

    //通过二分法找 找一个数的平方是否 等于target 存在返回true 不存在返回false
    public boolean search(long left, long right, int target) {
        while(left <= right) {
            long mid = left + (right - left) / 2;
            if(mid * mid == target) {
                return true;
            }
            if(mid * mid > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
```

### 思路2
在枚举i之后，我们也可以直接使用 系统库 sqrt 函数直接找出 j, 替换上面二分法。
```   
    //必须i 是long类型 不然超时
    public boolean judgeSquareSum(int c) {
        for(long i = 0; i * i <= c; i++) {
            double j = Math.sqrt(c - i * i); //求根
            int temp = (int)j; //强转整数
            if(j == temp) {
                //如果相等 说明求根的值原先就是整数
                return true;
            }
        }
        return false;
    }
```