## 104.二叉树的最大深度
   
### 题目描述
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
```
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
```
返回它的最大深度 3 。


### 思路
直观的方法是通过递归来解决问题。利用递归DFS（深度优先搜索）策略：

从树根出发每下一层 深度+1，左右子树深度最大值即为树的最大深度。
```   
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    private int maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        int leftDepth = maxDepth(root.left, depth);
        int rigthDepth = maxDepth(root.right, depth);
        return leftDepth < rigthDepth ? rigthDepth : leftDepth;
    }
```