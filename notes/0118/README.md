## 118. 杨辉三角

### 题目描述
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
![杨辉三角](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

在杨辉三角中，每个数是它左上方和右上方的数的和。
```
示例 :

输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

```



### 思路
简单题，每个数是它左上方和右上方的数的和。注意遍历时集合添加到第一个元素和最后一个元素的时候，直接取前一行的第一个和最后一个数就行。
```   
    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            if (i == 0) {
                list = Arrays.asList(1);
            } else {
                List<Integer> pre = data.get(i - 1);
                for (int j = 0; j <= i; j++) {
                    if (j == 0) { //pre第一元素
                        list.add(pre.get(0));
                    } else if (j == i) {  //pre最后一个元素 pre[size-1]
                        list.add(pre.get(j - 1));
                    } else {
                        list.add(pre.get(j-1) + pre.get(j));
                    }
                }
            }
            data.add(list);
        }
        return data;
    }
```

