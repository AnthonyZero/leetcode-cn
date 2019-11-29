## 232. 用栈实现队列

### 题目描述
使用栈实现队列的下列操作：

* push(x) -- 将一个元素放入队列的尾部。
* pop() -- 从队列首部移除元素。
* peek() -- 返回队列首部的元素。
* empty() -- 返回队列是否为空。



示例1:
```
MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // 返回 1
queue.pop();   // 返回 1
queue.empty(); // 返回 false
```

说明：
* 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
* 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
* 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。

### 思路
使用两个栈，一个输入栈，一个输出栈（输出栈 栈顶是队列队首），每次入队的时候操作两个栈，相互交换数据。最后输出栈就是我们的队列，后面pop、peek、empty等操作就直接操作输出栈就可以了。

```   
    class MyQueue {
        private Stack<Integer> inStack;
        private Stack<Integer> outStack;
        //
        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        //入队  每次维护输出栈即可  把输出栈的数据—>输入栈 压入元素 然后输入栈数据 -->输出栈
        public void push(int x) {
            while(!outStack.empty()) {
                inStack.push(outStack.pop());
            }

            inStack.push(x);  //这时候是输入栈完整数据

            while(!inStack.empty()) {  //把输入栈的数据 压入到输出栈 这时候输出栈就是我们的队列 栈顶就是队首
                outStack.push(inStack.pop());
            }
        }

        //出队
        public int pop() {
            return outStack.pop();
        }

        //队首
        public int peek() {
            return outStack.peek();
        }

        //判断是否为空
        public boolean empty() {
            return outStack.empty();
        }
    }
```