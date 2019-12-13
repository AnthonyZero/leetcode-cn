## 701. 二叉搜索树中的插入操作

### 题目描述
给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

例如：
```
给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和 插入的值: 5
```

你可以返回这个二叉搜索树:
```
         4
       /   \
      2     7
     / \   /
    1   3 5
```

或者这个树也是有效的:
```
         5
       /   \
      2     7
     / \   
    1   3
         \
          4
```


### 思路
很简单，就是二叉搜索树中插入元素。BST的常规操作：新元素总是在叶子节点
* 插入元素如果小于父节点 -》父节点左指针 关联上
* 插入元素如果大于父节点 -》父节点右指针 关联上
 
```   
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
```

