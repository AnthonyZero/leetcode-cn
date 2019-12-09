## 98.验证二叉搜索树

### 题目描述
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

* 节点的左子树只包含小于当前节点的数。
* 节点的右子树只包含大于当前节点的数。
* 所有左子树和右子树自身必须也是二叉搜索树。


示例1:
```
输入:
    2
   / \
  1   3
输出: true
```

示例2:
```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```

### 思路1
要判断是否是一个有效的二叉搜索树，那么它的中序遍历是一个有序的集合（从小到大排列）。借助一个集合验证中序遍历结果是否是有序的即可。

```   
    private List<Integer> list = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        inOrder(root);
        for(int i = 0; i < list.size()-1; i++) {
            if (list.get(i+1) <= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
```

### 思路2
前面使用了额外的集合空间，优化在于：可以在中序遍历的过程中，每次判断的时候用上一个值跟当前root.val比较，判断是否升序，省了单独的数组校验升序业务。

关键点在于搜索二叉树中序遍历完毕后是一个递增的序列,只要能将序列的前驱和后继进行比较 确定是一个递增的就可以了
```   
    private double min = -Double.MAX_VALUE; //注意临界值
    public  boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST(root.left)) {
            if (root.val > min) {
                min = root.val;
                return isValidBST(root.right);
            }
            return false;
        }
        return false;
    }
```   