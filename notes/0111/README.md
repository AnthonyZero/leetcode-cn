## 111.二叉树的最小深度
   
### 题目描述
给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明: 叶子节点是指没有子节点的节点。

示例：
```
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
```
返回它的最小深度  2.


### 思路
注意1,2 这个测试用例结果应该是2 不是1。 这里1不是叶子节点，2才是。因此推出递归结束条件如下：
* 叶子节点的定义是左孩子和右孩子都为 null 时叫做叶子节点
* 当节点左右孩子都为空时，返回 1, 因此每次返回最小深度的时候 要+1（就是包含了父节点这一层）
* 当节点左右孩子其中有一个为空时，这时还没到叶子节点，此时应该返回不为空的孩子节点的最小深度
* 当节点左右孩子都不为空时，进行比较取最小值，所以返回左右孩子较小的最小深度。

```   
    ppublic int minDepth(TreeNode root) {
         if (root == null) {
             return 0;
         }
         if (root.left == null && root.right != null) {
             return minDepth(root.right) + 1;
         } else if (root.left != null && root.right == null) {
             return minDepth(root.left) + 1;
         } else {
             return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
         }
     }
```