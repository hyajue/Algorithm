/**
* Implement the following operations of a stack using queues.
* 
* push(x) -- Push element x onto stack.
* pop() -- Removes the element on top of the stack.
* top() -- Get the top element.
* empty() -- Return whether the stack is empty.
* Notes:
* You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
* Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
* You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
*/

/*
复杂度
时间： 
push:O(n) pop:O(1) top：O(1)

思路：双队列
声明两个队列queue1 queue2 
所有元素倒序保存在queue1中：后来的元素往queue1的最前面放 实现方法如下：
每次push，把新元素放到queue2，然后把queue1中的元素逐个添加到queue2的队尾，接着交换queue1和queue2
这样queue1队头元素就是最后添加的元素 pop和push直接返回queue1对头元素 
*/

public class MyStack {
	
	private Queue<Integer> queue1;
	private Queue<Integer> queue2;

	public MyStack() {
		queue1 = new LinkedList<Integer>();
		queue2 = new LinkedList<Integer>();
	}
	
	/** Push element x onto stack. */
	public void push(int x) {
	  queue2.offer(x);
		//把queue1的元素挪到queue2里面来
		while (!queue1.isEmpty()) {
			queue2.offer(queue1.poll());
		}
		//交换queue1 and queue2 指针
		Queue tmp = queue1;
		queue1 = queue2;
		queue2 = tmp;
	}
	
	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
	  return queue1.poll();		
	}
	
	/** Get the top element. */
	public int top() {
	  return queue1.peek();  		
	}
	
	/** Returns whether the stack is empty. */
	public boolean empty() {
	  return queue1.isEmpty();		
	}
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */ 
