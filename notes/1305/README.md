## 1305. 两棵二叉搜索树中的所有元素

### 题目描述
给你 root1 和 root2 这两棵二叉搜索树。

请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。

示例1：
![图](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/12/29/q2-e1.png)
```
输入：root1 = [2,1,4], root2 = [1,0,3]
输出：[0,1,1,2,3,4]
```

示例2：
```
输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
输出：[-10,0,0,1,2,5,7,10]
```

示例3：
```
输入：root1 = [], root2 = [5,1,7,0,2]
输出：[0,1,2,5,7]
```

示例4：
```
输入：root1 = [0,-10,10], root2 = []
输出：[-10,0,10]
```

示例5：
```
输入：root1 = [1,null,8], root2 = [8,1]
输出：[1,1,8,8]
```
提示：
* 每棵树最多有 5000 个节点。
* 每个节点的值在 [-10^5, 10^5] 之间。


### 思路
利用二叉搜索树本身的性质。如果我们对二叉搜索树进行中序遍历，就可以直接得到树中所有元素升序排序后的结果。
因此我们可以对两棵树分别进行中序遍历，得到数组 v1 和 v2，它们分别存放了两棵树中的所有元素，且均已有序。
在这之后，我们通过归并排序的方法对 v1 和 v2 进行排序，就可以得到最终的结果。
```   
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        //得到两个树的排序集合
        List<Integer> rootList1 = new ArrayList<>();
        List<Integer> rootList2 = new ArrayList<>();
        dfs(root1, rootList1);
        dfs(root2, rootList2);
        //对两个已排序好的集合 进行归并（归并排序中重要的归并过程）
        int left = 0;
        int right = 0;
        List<Integer> res = new ArrayList<>();
        while(left < rootList1.size() && right < rootList2.size()) {
            //哪个比较小添加进去
            if (rootList1.get(left) < rootList2.get(right)) {
                res.add(rootList1.get(left));
                left++;
            } else {
                res.add(rootList2.get(right));
                right++;
            }
        }
        //保证其中剩余的元素添加进去
        while(left < rootList1.size()) {
            res.add(rootList1.get(left));
            left++;
        }
        while(right < rootList2.size()) {
            res.add(rootList2.get(right));
            right++;
        }
        return res;
    }

    //中序遍历 得到排序的集合
    private void dfs(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }   
```   

