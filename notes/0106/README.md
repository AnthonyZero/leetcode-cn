## 106.从中序与后序遍历序列构造二叉树
   
### 题目描述
根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

示例：
```
中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]

返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
```

### 思路
因为后序遍历为左右根。 中序遍历为左根右。

所以后序遍历的最后一个元素为重建的二叉树的根节点的值。

根据中序遍历结果，找到和根节点值相同的位置。则此元素左边的都是根节点的左子树的元素，右边的都是根节点右子树的元素。

通过递归很容易求出解。


比如：

中序遍历 inorder = [9,3,15,20,7]

后序遍历 postorder = [9,15,7,20,3]

后序遍历最后一个值3是这个树的根，在中序遍历结果中找到3这个元素的位置。3左边的元素都都是左子树元素[9]，右边的元素都是右子树元素[15,20,7]

因为后序遍历是左右根，继续递归下去除了分割中序遍历，还要分割后序遍历。此时根据根3的左子树的元素个数，很容易分割出后序遍历（3已经不要了）。 左：[9] 右：[15,7,20] 
```   
    private Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        return root;
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                               int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int val = postorder[postEnd]; //取后序最后一位 为根
        int index = map.get(val); //根在前序 中的位置。
        TreeNode root = new TreeNode(val);
        int postOffset = index - 1 - inStart;  //后序数组的偏移量 根据左右 根前提 不要根断开取左右子树

        root.left = buildTree(inorder, inStart, index - 1, postorder,
                postStart, postStart + postOffset);

        root.right = buildTree(inorder, index + 1, inEnd, postorder,
                postStart + postOffset + 1, postEnd - 1);
        return root;
    }
```