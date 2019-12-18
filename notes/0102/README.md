## 102.二叉树的层次遍历
   
### 题目描述
给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

例如:
给定二叉树: [3,9,20,null,null,15,7],

示例：
```
    3
   / \
  9  20
    /  \
   15   7
```
返回其层次遍历结果：

```
[
  [3],
  [9,20],
  [15,7]
]
```


### 思路
获取二叉树的层序结果：采用队列（元素满足 先进先出的原则）。先入队根节点。

然后循环遍历队列是否为空。不为空的话根据当前队列的元素个数（也就是每一层节点个数）, 进行出队操作。

> 当前队列的元素个数 等于 我们遍历到第几层的 这一层的节点个数
```   
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int nodeSize = queue.size(); //当前层节点个数
            List<Integer> floor = new ArrayList<>();
            for (int i = 0; i < nodeSize; i++) {
                TreeNode node = queue.remove();
                floor.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(floor);
        }
        return list;
    }
```