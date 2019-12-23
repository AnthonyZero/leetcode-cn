## 707. 设计链表

### 题目描述
设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

* get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
* addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
* addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
* addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
* deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。

例如：
```
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3

```

### 思路
借助一个虚拟节点dummyHead,在实现链表插入 删除操作的时候很方便。

```   
    class MyLinkedList {
        private int size;
        private ListNode dummyHead;

        public MyLinkedList() {
            dummyHead = new ListNode(-1);
            size = 0;
        }

        // 0 到 size-1
        public int get(int index) {
            if (index >= size) {
                return -1;
            }
            ListNode pre = dummyHead;
            for (int i = 0; i <= index; i++) {
                pre = pre.next;
            }
            return pre.val;
        }


        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }


        public void addAtIndex(int index, int val) { //index  0 到size
            if (index > size)
                return;
            else {
                ListNode pre = dummyHead;
                for (int i = 0; i < index; i++) {
                    pre = pre.next;
                }
                ListNode node = new ListNode(val);
                node.next = pre.next;
                pre.next = node;
                size++;
            }
        }

        public void deleteAtIndex(int index) {  index [0, size-1]
            if (index < 0 || index >= size) {
                return;
            }
            ListNode pre = dummyHead;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            ListNode delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }
```

