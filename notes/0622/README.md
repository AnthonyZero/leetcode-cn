## 622. 设计循环队列

### 题目描述
设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。

循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。

你的实现应该支持如下操作：
* MyCircularQueue(k): 构造器，设置队列长度为 k 。
* Front: 从队首获取元素。如果队列为空，返回 -1 。
* Rear: 获取队尾元素。如果队列为空，返回 -1 。
* enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
* deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
* isEmpty(): 检查循环队列是否为空。
* isFull(): 检查循环队列是否已满。


示例:

```
MyCircularQueue circularQueue = new MycircularQueue(3); // 设置长度为 3

circularQueue.enQueue(1);  // 返回 true

circularQueue.enQueue(2);  // 返回 true

circularQueue.enQueue(3);  // 返回 true

circularQueue.enQueue(4);  // 返回 false，队列已满

circularQueue.Rear();  // 返回 3

circularQueue.isFull();  // 返回 true

circularQueue.deQueue();  // 返回 true

circularQueue.enQueue(4);  // 返回 true

circularQueue.Rear();  // 返回 4
```


### 思路
这题不考虑扩容情况。线性结构队列底层还是数组，用头指针指向队列头，尾指针指向下一个元素入队的位置。因为是循环队列，无法区分队空和队满的状态，因为队空和队满的条件都是tail==front。所以在这借助一个存储单元（相当于初始化的时候多初始一个位置，只是用于判断而不存储实际值），这时候队空的条件就可以是tail==front, 队满的条件为front=(tail+1)%data.length（队列满的时候还剩一个存储槽）。

* 注意存储单位不是指数组最后一个槽，当tail指向最后一个槽，front在0这个位置，这时候队列是满的。但是如果这时候出队了，front就在1这个位置了，这时候判断(tail+1)%data.length 不等于front,所以这时候队列是没满的情况，可以继续入队，而tail所在位置（数组最后一个槽）填上了入队的元素，tail就指向了0这个位置。这时候反而第0个位置是空的。 所以说用来判断的这多出的一个槽不是指数组最后一个槽。

```   
    class MyCircularQueue {
        int[] data;  //data.length = k+1 保存k个元素
        int front;
        int tail;
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            data = new int[k + 1]; //预留一个位置（用于判断队列是否满的情况） 不存元素(并不是指数组不存的是最后一个元素)
            front = 0;
            tail = 0;
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            data[tail] = value;
            tail = (tail + 1) % data.length; //移动一个位置
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            data[front] = 0;
            front = (front + 1) % data.length;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return data[front];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if(isEmpty()) {
                return -1;
            }
            if (tail == 0) {
                return data[data.length - 1];
            } else {
                return data[tail - 1];
            }
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return front == tail;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return (tail + 1) % data.length == front;
        }
    }
```

