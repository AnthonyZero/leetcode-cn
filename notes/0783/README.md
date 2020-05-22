## 783.二叉搜索树节点最小距离

### 题目描述
给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。

示例：
```
输入: root = [4,2,6,1,3,null,null]
输出: 1
解释:
注意，root是树节点对象(TreeNode object)，而不是数组。

给定的树 [4,2,6,1,3,null,null] 可表示为下图:

          4
        /   \
      2      6
     / \    
    1   3  

最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
```

注意：

1. 二叉树的大小范围在 2 到 100。
2. 二叉树总是有效的，每个节点的值都是整数，且不重复。
3. 本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同


### 思路
题目要求是找任意两节点的最小差值，那么在二叉搜索树中，中序遍历会将树中节点按数值大小顺序输出。只需要遍历的时候计算相邻数的差值，取其中最小的就可以了。
> 因为是有序的，所以相邻之间的元素差是最小的。
```   
    public Integer preElement; //遍历的过程中 前一个元素
    public int minDiff = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        if(root == null) {
            return -1;
        }
        inOrder(root);
        return minDiff;
    }

    // 中序遍历
    private void inOrder(TreeNode root) {
        if(root == null) {
            return;
        }
        inOrder(root.left);
        if(preElement == null) {
            preElement = root.val;
        } else {
            int differ = Math.abs(root.val - preElement);
            if (differ < minDiff) {
                minDiff = differ;
            }
            preElement = root.val;
        }
        inOrder(root.right);
    }
```

