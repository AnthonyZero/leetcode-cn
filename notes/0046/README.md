## 46. 全排列

### 题目描述
给定一个没有重复数字的序列，返回其所有可能的全排列。

```
示例 :
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

```

### 思路
怎么穷举全排列的呢？比方说给三个数 [1,2,3]，那么就是1和[2,3]的全排列；2和[1,3]的全排列；
3和[1,2]的全排列的所有可能。

怎么求：回溯，从根遍历这棵树（决策树），记录路径上的数字。每当走到树的底层，其「路径」就是一个全排列。
主要的是在递归返回之后撤销刚才的选择，让下一次路径搜索的时候才能选择先前撤销的（选择）。在回到上一层结点的过程中，需要撤销上一次选择，这个操作也称之为“状态重置”。
> 已经选择的数字在接下来要确定的数字中不能出现。按照这种策略选取就能够做到不重不漏，把可能的全排列都枚举出来
```   
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, List<Integer> prefix) {
        if (prefix.size() == nums.length) {
            //到达树底部
            //res.add(prefix); //值传递 这样添加会导致递归返回的时候prefix会remove元素 得到的是空集合
            res.add(new ArrayList<>(prefix));
            return;
        }
        for (int i = 0; i < nums.length; i++) { //当开始循环到1 2... 的时候 因为有撤销选择恢复的操作 used数组全是false
            if (!used[i]) { //没使用过
                used[i] = true;
                prefix.add(nums[i]);
                backtrack(nums, prefix);
                //恢复状态 让其他递归路径 可利用(很重要)
                prefix.remove(Integer.valueOf(nums[i]));
                used[i] = false;
            }
        }
        return;
    }
```

