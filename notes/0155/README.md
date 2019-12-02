## 155. 最小栈

### 题目描述
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

* push(x) -- 将元素 x 推入栈中。
* pop() -- 删除栈顶的元素。
* top() -- 获取栈顶元素。
* getMin() -- 检索栈中的最小元素。

示例1:
```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.

```
### 思路
只用一个变量去保存最小值：为了不丢失上一个最小值，那么就当遇到新的最小值的时候，把上一个最小值同时先压入栈中。出栈的时候如果出栈的刚好是当前最小值，那么就要多出栈一个元素（这个元素用来更新最小值，因为它是上一个最小值）。

注意点：出栈最小值的时候是判断等于情况。那么在入栈的时候如果当前值是最小值，那么也要相对应的多压一次。避免多个相同最小值出栈多出了一个，而入栈没有多压一次导致栈空间异常。

```   
    class MinStack {
        private int min = Integer.MAX_VALUE; //最小值
        private Stack<Integer> stack;
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (x <= min) {
                //更新最小值 顺便要前一个最小值压入栈
                stack.push(min);
                stack.push(x);
                min = x;
            } else {
                stack.push(x);
            }
        }

        public void pop() {
            if (stack.peek() == min) {
                //如果要出栈的元素是我们的最小值
                stack.pop(); //最小值出栈
                min = stack.pop();  //更新最小值 为前一个最小值
            } else {
                stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
```