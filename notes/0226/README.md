## 226. 翻转二叉树

### 翻转一棵二叉树。

示例：

输入：
```  
     4
   /   \
  2     7
 / \   / \
1   3 6   9

```  

输出：
```  
     4
   /   \
  7     2
 / \   / \
9   6 3   1

```  

### 思路
利用前序遍历二叉树，递归交换左右子树

```   
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 左子树和右子树交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归翻转左右子树
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
```