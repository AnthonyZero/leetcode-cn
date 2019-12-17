## 105.从前序与中序遍历序列构造二叉树
   
### 题目描述
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

示例：
```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]

返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
```

### 思路
因为前序遍历为根左右。 中序遍历为左根右。

所以前序遍历的第一个元素为重建的二叉树的根节点的值。

根据中序遍历结果，找到和根节点值相同的位置。则此元素左边的都是根节点的左子树的元素，右边的都是根节点右子树的元素。

通过递归很容易求出解。


比如：

前序遍历 preorder = [3,9,20,15,7]

中序遍历 inorder = [9,3,15,20,7]

前序遍历第一个值3是这个树的根，在中序遍历结果中找到3这个元素的位置。3左边的元素都都是左子树元素[9]，右边的元素都是右子树元素[15,20,7]

因为前序遍历是根左右，继续递归下去除了分割中序遍历，还要分割前序遍历。此时根据根3的左子树的元素个数 这里只有[9]一个元素，很容易分割前序遍历（根元素3已经不要了）。 左：[9] 右：[20,15,7] 
```   
    private Map<Integer,Integer> map = new HashMap<>(); //中序
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart]; //根的值
        int index = map.get(rootVal);  //根在中序遍历中的 位置
        TreeNode root = new TreeNode(rootVal);
        int offset = index - 1 - inStart; //偏移量

        root.left = buildTree(preorder, preStart + 1, preStart + 1 + offset, inorder, inStart, index - 1);
        root.right = buildTree(preorder, preStart + 1 + offset + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }
```