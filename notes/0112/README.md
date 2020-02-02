## 112.路径总和
   
### 题目描述
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

示例: 
给定如下二叉树，以及目标和 sum = 22，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1

```
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。


### 思路
跟111题目类似，注意递归结束条件（有陷阱）叶子节点的定义是左孩子和右孩子都为 null 时叫做叶子节点。
当递归到达叶子节点的时候判断是否叶子节点，且当前节点值等于当前sum（sum每下一层作减法）；左右子树只要存在一条路即可
```   
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == sum; //递归终止条件 左右孩子为空才是叶子节点 到了叶子节点判断剩下的值是否等于sum
        }
        //每下一层 sum减去当前节点的值
        if (hasPathSum(root.left, sum - root.val)) {
            return true;
        }
        if (hasPathSum(root.right, sum - root.val)) {
            return true;
        }
        return false;
    }
```