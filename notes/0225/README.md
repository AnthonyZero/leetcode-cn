## 225. 用队列实现栈

### 题目描述
使用队列实现栈的下列操作：

* push(x) -- 元素 x 入栈
* pop() -- 移除栈顶元素
* top() -- 获取栈顶元素
* empty() -- 返回栈是否为空


说明：
* 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
* 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
* 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。

### 思路
使用一个队列，不使用自带的双端队列等。入栈的时候栈顶元素top更新为当前入队元素。top()方法就很简单直接返回top。

出栈的时候，要删除栈顶元素：可考虑我们队列前面n-1个元素先后出队，然后重新陆续入队。 那么现在队头元素就是 要删的元素（我们的栈顶元素）。利用队列出队就可以满足我们的出栈功能。同时不要忘了出队入队过程中更新top值。

```   
    class MyStack {
        private Queue<Integer> queue;
        private int top; //保存队尾数字 代表栈顶
        public MyStack() {
            queue = new LinkedList<>();
        }

        // 添加到队尾
        public void push(int x) {
            queue.offer(x);
            top = x;
        }

        //思路在于 把队列前面n-1个元素 出队后陆续先后顺序 重新入队。 那么队头元素就是 要删的元素（栈顶元素）
        public int pop() {
            int size = queue.size();
            for (int i = 0; i < size-1; i++) {
                int element = queue.poll(); //出队 队首第一个元素
                queue.offer(element);  //又入队
                top = element;  //更新栈顶元素
            }
            //那现在队首元素 就是top
            return queue.poll(); //移除队首元素 第一个元素
        }

        //获取栈顶元素  = 队尾
        public int top() {
            return top;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
```