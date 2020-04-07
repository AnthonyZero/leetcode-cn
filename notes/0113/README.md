## 113.路径总和 II
   
### 题目描述
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

返回:

[
   [5,4,11,2],
   [5,8,4,5]
]
```

### 思路1
深度优先搜索：每次递归（深度树进行下一层的时候，拷贝创建一个新的集合对象，到达叶子节点满足条件的话就加入当前结果）
```   
    private List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        pathSum(root, sum, new ArrayList<>());
        return res;
    }

    public void pathSum(TreeNode root, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }
        //叶子节点
        if(root.left == null && root.right == null) {
            if(root.val == sum) {
                list.add(root.val);
                res.add(list);
            }
            return;  //递归返回
        }
        list.add(root.val);
        pathSum(root.left, sum - root.val, new ArrayList<>(list));
        pathSum(root.right, sum - root.val, new ArrayList<>(list));
    }
```

### 思路2
深度优先搜索（回溯）：在搜索时可以传递list的引用，再通过回溯的方法来确保list的正确性，即每次访问完一条路径后，回溯，即删除list的最后一个元素，让list恢复到正确的状态，再接着另一条路径的访问。
```
    private List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        dfs(root, sum, new ArrayList<>());
        return res;
    }

    //回溯的方式
    public void dfs(TreeNode root, int sum, List<Integer> list) {
        if(root == null) {
            return;
        }
        list.add(root.val);
        sum = sum - root.val;
        if(root.left == null && root.right == null && sum == 0) {
            //叶子节点 深度搜索到底了
            res.add(new ArrayList<>(list)); //添加一个分支
            list.remove(list.size() - 1);
            return;  //递归返回
        }
        //只有两个选择 左树 右树
        dfs(root.left, sum, list);
        dfs(root.right, sum, list);
        //回溯(撤销上次添加根节点的选择)
        list.remove(list.size() - 1);
    }

```