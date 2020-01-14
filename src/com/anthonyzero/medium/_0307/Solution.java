package com.anthonyzero.medium._0307;


public class Solution {

    interface Merger<E> {
        E merger(E a, E b); //怎么融合线段树两个区间 根据自己业务决定
    }

    class SegmentTree<E> {

        private E[] tree;  //根据业务决定后 建立的线段树 的数组 保存每个区间的结果 4*data.length
        private E[] data;  //元素数据
        private Merger<E> merger;

        public SegmentTree(E[] arr, Merger<E> merger) {
            this.merger = merger;

            data = (E[]) new Object[arr.length];
            for (int i = 0; i < arr.length; i++)
                data[i] = arr[i];

            tree = (E[]) new Object[4 * arr.length]; //4n容量

            // 从树根开始递归 根据原数据来构造 线段树 tree[0] tree[1] tree[2]
            buildSegmentTree(0, 0, data.length - 1);
        }


        // 在treeIndex的位置创建表示区间[l...r]的线段树
        private void buildSegmentTree(int treeIndex, int left, int right) {
            if (left == right) {
                //递归到底部 这里建立叶子节点 此时节点的值 就是原数据数组某个索引的值
                tree[treeIndex] = data[left];
                return;
            }

            int leftChildIndex = leftChild(treeIndex); //左子树所在tree数组中索引
            int rightChildIndex = rightChild(treeIndex); // 右子树所在tree数组中索引

            // int mid = (l + r) / 2 可能溢出;
            int middle = left + (right - left) / 2;
            buildSegmentTree(leftChildIndex, left, middle);
            buildSegmentTree(rightChildIndex, middle + 1, right);

            //根据业务来 merge可以求和 求最大值等等  从这里递归返回的过程 就是从底往上建树的过程
            tree[treeIndex] = merger.merger(tree[leftChildIndex], tree[rightChildIndex]);
        }

        public int getSize() {
            return data.length;
        }

        public E get(int index) {
            if (index < 0 || index >= data.length)
                throw new IllegalArgumentException("Index is illegal.");
            return data[index];
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
        private int leftChild(int index) {
            return 2 * index + 1;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
        private int rightChild(int index) {
            return 2 * index + 2;
        }


        // 返回区间[queryL, queryR]的值 data数组范围内
        public E query(int queryL, int queryR) {

            if (queryL < 0 || queryL >= data.length ||
                    queryR < 0 || queryR >= data.length || queryL > queryR)
                throw new IllegalArgumentException("Index is illegal.");

            return query(0, 0, data.length - 1, queryL, queryR);
        }

        // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
        private E query(int treeIndex, int l, int r, int queryL, int queryR) {
            if (l == queryL && r == queryR) { //到达叶子节点
                return tree[treeIndex];
            }

            int mid = l + (r - l) / 2;
            // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
            int leftChildIndex = leftChild(treeIndex);
            int rightChildIndex = rightChild(treeIndex);

            if (queryL >= mid + 1) {
                //要搜索的区间都落在右边
                return query(rightChildIndex, mid + 1, r, queryL, queryR);
            } else if (queryR <= mid) {
                //要搜索的区间都落在左边
                return query(leftChildIndex, l, mid, queryL, queryR);
            } else {
                //要搜索的区间 在左右两边的区间都有
                E leftResult = query(leftChildIndex, l, mid, queryL, mid);
                E rightResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
                return merger.merger(leftResult, rightResult);
            }
        }

        // 将index位置的值，更新为e
        public void set(int index, E e) {

            if (index < 0 || index >= data.length)
                throw new IllegalArgumentException("Index is illegal");

            data[index] = e;
            set(0, 0, data.length - 1, index, e);
        }

        // 在以treeIndex为根的线段树中更新index的值为e
        private void set(int treeIndex, int l, int r, int index, E e) {

            if (l == r) {
                tree[treeIndex] = e;
                return;
            }

            int mid = l + (r - l) / 2;
            // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if (index >= mid + 1)
                set(rightTreeIndex, mid + 1, r, index, e);
            else // index <= mid
                set(leftTreeIndex, l, mid, index, e);

            // 在线段树上index上面的各个线段 同时也要更新
            tree[treeIndex] = merger.merger(tree[leftTreeIndex], tree[rightTreeIndex]);
        }
    }

    class NumArray {

        private SegmentTree<Integer> segmentTree;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                Integer[] array = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    array[i] = nums[i];
                }
                segmentTree = new SegmentTree<Integer>(array, (a, b) -> a + b);
            }
        }

        public void update(int i, int val) {
            segmentTree.set(i, val);
        }

        public int sumRange(int i, int j) {
            return segmentTree.query(i, j);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5};
        NumArray numArray = new Solution().new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }
}
