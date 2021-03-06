## 47. 全排列 II

### 题目描述
给定一个可包含重复数字的序列，返回所有不重复的全排列。

```
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

```

### 思路
回溯+剪枝：在搜索之前就对候选数组排序，一旦发现这一支搜索下去可能搜索到重复的元素就停止搜索，这样结果集中不会包含重复元素。
```   
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        used = new boolean[nums.length];
        //必须要先排序啊,这样只有相邻的才可能相等，才可以判断去重(剪枝)
        Arrays.sort(nums);
        backtrack(nums, new LinkedList<>());
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> prefix) {
        if (prefix.size() == nums.length) {
            //到达底部
            res.add(new LinkedList<>(prefix));
        }
        for(int i = 0; i < nums.length; i++) {
            //这个位置用过了
            if (used[i]) {
                continue;
            }
            //选择i的时候 i-1和i的值相等（这一层数字相同 可能重复） 且i-1没被用过
            //若某一个元素和上一个元素相等,并且上一个元素没有被使用过，说明它俩在同一层(这种情况就是重复的);
            //若某一个元素和上一个元素相等,并且上一个元素被使用过，说明这个元素在上一个元素的子树中
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            prefix.add(nums[i]);
            backtrack(nums, prefix);
            //回溯
            used[i] = false;
            prefix.removeLast();
        }
    }
```

