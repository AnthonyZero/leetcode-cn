## 437.路径总和 III

### 题目描述
给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：
```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11
```

### 思路
路径的开头可以不是根节点，结束也可以不是叶子节点：那么我们在选择节点的时候有两种情况：这个
节点要么包含，要么不包含。 

用两个递归函数：一个递归算以node为根节点的二叉树中,寻找包含node的路径,和为sum的所有情况，
另一个函数就是在以root为根节点的二叉树中,寻找和为sum的路径,所有的路径个数。

```   
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = findPath(root, sum); //包含当前节点的所有路径
        //不要当前节点的所有结果路径
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        return res;
    }

    // 在以node为根节点的二叉树中,寻找包含node的路径,和为sum
    // 返回这样的路径个数
    private int findPath(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        if (node.val == sum) {
            res++;
        }
        res += findPath(node.left, sum - node.val);
        res += findPath(node.right, sum - node.val);
        return res;
    }
```
