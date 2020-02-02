## 257.二叉树的所有路径

### 题目描述
给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点

```
示例 :

输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
```

### 思路
递归：遇到叶子节点返回当前节点值。对于根节点而言：就是当前节点值加上左右子树所有路径的合并结果。
```   
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        if (root.left == null && root.right == null) {
            //叶子节点
            ArrayList<String> list = new ArrayList<>();
            list.add(String.valueOf(root.val));
            return list;
        }
        //合并左右子树的结果
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < left.size(); i++) {
            result.add(root.val + "->" + left.get(i));
        }
        for (int i = 0; i < right.size(); i++) {
            result.add(root.val + "->" + right.get(i));
        }
        return result;
    }
```
