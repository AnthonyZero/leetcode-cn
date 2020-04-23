## 1329.将矩阵按对角线排序

### 题目描述
给你一个 m * n 的整数矩阵 mat ，请你将同一条对角线上的元素（从左上到右下）按升序排序后，返回排好序的矩阵。

示例1：
![图](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/25/1482_example_1_2.png)
```

输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
```


提示：
* m == mat.length
* n == mat[i].length
* 1 <= m, n <= 100
* 1 <= mat[i][j] <= 100


### 思路
1. 找起点：两种情况：以第一行为起点（右上部分）；以第一列为起点（左下部分）；
2、从起点开始，找对角线元素，然后把对角线元素添加给集合中；对集合排序然后把排序后的每个元素放回原数组中。
```   
   public int[][] diagonalSort(int[][] mat) {
       int m = mat.length; //多少行 
       int n = mat[0].length; //多少列 
       for(int i = 0; i < n - 1; i++) { //排序右上部分的 对角线 有n条对角线 不要右上角的
           //起点横坐标为0 纵坐标y从0到 n-1
           int x = 0;
           int y = i;
           List<Integer> list = new ArrayList<>();
           while(x < m && y < n) {
               list.add(mat[x++][y++]);
           }
           Collections.sort(list);
           x = 0;
           y = i;
           for(int temp : list) {
               mat[x++][y++] = temp;
           }
       }
       for(int i = 1; i < m - 1; i++) { //排序左下部分的 对角线 有m-1条对角线 不要左下角的
           //起点横坐标为i从1到 m-1 纵坐标为0
           int x = i;
           int y = 0;
           List<Integer> list = new ArrayList<>();
           while(x < m && y < n) {
               list.add(mat[x++][y++]);
           }
           Collections.sort(list);
           x = i;
           y = 0;
           for(int temp : list) {
               mat[x++][y++] = temp;
           }
       }
       return mat;
   }
```   

