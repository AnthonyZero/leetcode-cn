## 109.有序链表转换二叉搜索树
   
### 题目描述
给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例：
```
给定的有序链表： [-10, -3, 0, 5, 9],

一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
```


### 思路1 
根据高度平衡的二叉搜索树每个节点 的左右两个子树的高度差的绝对值不超过1的定义：

解决方法：从顶向下构造树
1. 有序链表先转为排序数组 这样好直接取值O(1)
2. 查找中间值的过程中（二分法）递归的插入节点（节点值就是中间值）。

比如：[-10, -3, -1, 0, 5, 9]，先插入0，再在0左边的数据找中间值-3插入，0右边的数据找中间值9插入，陆续递归下去，直到找不到值结束，此时数据已经插入完毕。这个过程相当于二分搜索的过程。因为只有这样陆续插入 这颗树才是高度平衡的二叉搜索树。

```   
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        TreeNode root = searchMiddle(null, list, 0, list.size() - 1);
        return root;
    }

    //寻找中间值的过程 递归
    private TreeNode searchMiddle(TreeNode root, List<Integer> list, int i, int j) {
        if (i == j) {
            return insertNode(root, list.get(i));
        } else if (i > j) {
            return root;
        } else {
            // i < j;
            int middle = (i + j + 1) / 2;
            TreeNode node = insertNode(root, list.get(middle));
            searchMiddle(node, list, i, middle - 1);
            searchMiddle(node, list, middle + 1, j);
            return node;
        }
    }

    private TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else if (val > root.val) {
            root.right = insertNode(root.right, val);
        }
        return root;
    }
```

### 思路2

思路1上时间复杂度有点低，原因在于二分搜索的过程是一个递归，树插入节点的过程也是递归。其实这里完全可以通过一个递归解决（也是从顶向下构造树）。

1. 将给定链表转成数组，将数组的头和尾记成 left 和 right 。
2. 找到中间元素 (left + right + 1) / 2，记为 mid。这需要 O(1) 时间开销
3. 将中间元素作为二叉搜索树的根。
4. 递归构造二叉搜索树的左右两棵子树，两个子数组分别是 (left, mid - 1) 和 (mid + 1, right)。

总结：给定列表中的中间元素作为二叉搜索树的根，该点左侧的所有元素递归的去构造左子树，同理右侧的元素构造右子树。

```
    private List<Integer> list = new ArrayList<>();
    
    public TreeNode sortedListToBST(ListNode head) {
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        TreeNode root = createBST(0, list.size() - 1);
        return root;
    }

    private TreeNode createBST(int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            TreeNode node = new TreeNode(list.get(left));
            return node;
        } else {
            int middle = (left + right + 1) / 2;
            TreeNode root = new TreeNode(list.get(middle));
            root.left = createBST(left, middle - 1);
            root.right = createBST(middle + 1, right);
            return root;
        }
    }
```