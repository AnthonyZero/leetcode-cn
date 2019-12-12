## 110.平衡二叉树
   
### 题目描述
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

> 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例1：
```
给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
```
返回 true 。

示例2：
```
给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
```
返回 false 。


### 思路
根据它的定义：只要二叉树每个节点 的左右两个子树的高度差的绝对值不超过1就是平衡二叉树。

比较每个节点的左右子树的（maxDepth）最大高度差是否满足，递归下去整个树（左右子树）。
```   
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        int abs = Math.abs(leftHeight - rightHeight); //先看节点的左右子树高度 是否相差1以上
        if (abs <= 1) {
            //在1范围内 说明当前节点为根的树暂时是平衡二叉树   那么继续看它的左右节点 同样是不是平衡二叉树
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    //获取树的最大深度
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
```