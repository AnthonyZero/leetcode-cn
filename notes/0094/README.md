## 94.二叉树的中序遍历

### 题目描述
给定一个二叉树，返回它的中序遍历。

示例1:
```
输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

### 思路
递归。经典的方法，直截了当。定义一个辅助函数来实现递归。

```   
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> inorder = inorder(root, list);
        return inorder;
    }
    
    private List<Integer> inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
        return list;
    }
```

复杂度分析

时间复杂度：O(n)。递归函数 T(n) = 2 * T(n/2)+1。

空间复杂度：最坏情况下需要空间O(n)，平均情况为O(log n)。