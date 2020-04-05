## 101.对称二叉树
   
### 题目描述
给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
```
    1
   / \
  2   2
   \   \
   3    3
```

### 思路
如果一个树的左子树与右子树镜像对称，那么这个树是对称的。

因此，该问题可以转化为：两个树在什么情况下互为镜像？

如果同时满足下面的条件，两个树互为镜像：
1. 它们的两个根结点具有相同的值。
2. 每个树的右子树都与另一个树的左子树镜像对称。
```   
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isMirror(root, root);
    }

    //判断两个树 是否是对称的 （两个树 每个节点值 都比较是否相等）
    private boolean isMirror(TreeNode p, TreeNode q) {
        //都为空 那么肯定是对称的
        if(p == null && q == null) {
            return true;
        }
        //其中一个为空 不对称
        if(p == null || q == null) {
            return false;
        }
        //当前根节点相同的前提下
        if (p.val == q.val) {
            //要判断p的左树与q的右树对称  且 p的右树与q的左树对称
            return isMirror(p.left, q.right) && isMirror(p.right, q.left);
        }
        return false;
    }
```
复杂度分析

时间复杂度：O(n)，因为我们遍历整个输入树一次，所以总的运行时间为 O(n)，其中 n是树中结点的总数。

空间复杂度：递归调用的次数受树的高度限制。在最糟糕情况下，树是线性的，其高度为 O(n)。因此，在最糟糕的情况下，由栈上的递归调用造成的空间复杂度为 O(n)。