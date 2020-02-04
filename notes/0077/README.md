## 77.组合

### 题目描述
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:
```
输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

### 思路1
回溯+剪枝
```   
     private List<List<Integer>> res = new LinkedList<>();
     public List<List<Integer>> combine(int n, int k) {
         if (n <= 0 || k <= 0 || k > n) {
             return res;
         }
         backtrack(n, k, 1, new LinkedList<>());
         return res;
     }
    
     // 当前已经找到的组合存储在prefix中, 需要从start开始搜索新的元素
     private void backtrack(int n, int k, int start, LinkedList<Integer> prefix) {
         if (prefix.size() == k) {
             res.add(new LinkedList<>(prefix));
             return;
         }
         // 剪枝
         // 还有k - c.size()个空位, 所以, [i...n] 中至少要有 k - c.size() 个元素
         // i最多为 n - (k - c.size()) + 1
         for (int i = start; i <= n - (k - prefix.size()) + 1; i++) {
             prefix.add(i);
             backtrack(n, k, i+1, prefix);
             prefix.removeLast(); //举例 当prefix为[1,2]递归返回的时候，此时循环取3的时候，
             // 需要把上一次选择2删除掉 恢复状态
         }
         return;
     }
```