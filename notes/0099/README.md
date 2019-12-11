## 99.恢复二叉搜索树
   
### 题目描述
二叉搜索树中的两个节点被错误地交换。

请在不改变其结构的情况下，恢复这棵树。

示例1:
```
输入: [1,3,null,null,2]

   1
  /
 3
  \
   2

输出: [3,1,null,null,2]

   3
  /
 1
  \
   2
```

示例2:
```
输入: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

输出: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
```

### 思路1
通俗易懂的解法：要交换两个错误的节点（不变原来的树结构）。可知二叉搜索树的中序遍历是一个升序的结果。如果中序遍历此时错误的二叉搜索树，那么肯定有2个值是对应不上它原本的排序结果。那么恰好可以利用这点：

我们可以先求出此时的二叉搜索树的中序遍历集合，然后升序排序。最后再一次遍历此时错误的二叉树，如果当前节点和 与我们排序后的集合所在位置的值不相等。直接修改当前节点值即可。 最后就修正了此二叉树其中错误的两个节点值（并没有改变树的结构）。

> 原理是：二叉搜索树的中序遍历一定是单调递增的，所以sort完之后一定就是对应的正确的结果，那么再对错误的二叉树中序遍历，逐个比对修改就好

```   
    private List<Integer> result = null;
    private int index;

    public void recoverTree(TreeNode root) {
        result = inOrderList(root, new ArrayList<>());
        Collections.sort(result); //默认升序排序
        recoverTreeNode(root);
    }

    private void recoverTreeNode(TreeNode root) {
        if (root == null) {
            return;
        }
        recoverTreeNode(root.left);
        if (root.val != result.get(index)) {
            root.val = result.get(index);
        }
        index++;
        recoverTreeNode(root.right);
    }

    /**
     * 获取中序遍历集合
     * @param root
     * @param list
     * @return
     */
    private List<Integer> inOrderList(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        inOrderList(root.left, list);
        list.add(root.val);
        inOrderList(root.right, list);
        return list;
    }
```