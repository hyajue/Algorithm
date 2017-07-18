/**
* Implement the following operations of a queue using stacks.
* 
* push(x) -- Push element x to the back of queue.
* pop() -- Removes the element from in front of queue.
* peek() -- Get the front element.
* empty() -- Return whether the queue is empty.
* Notes:
* You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
* Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
* You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

/*
复杂度
时间： push O(1) pop or peek O(n)

思路：双栈模拟
队列和栈都是顺序插入的，区别在于队列的出口方向和栈的出口方向是相反的，
利用这个性质，如果我们将元素按照顺序插入栈后，再一个个弹出并反向插入另一个栈，那么这第二个栈的出口顺序就和队列是一样的了
所以我们构造两个栈，所有新加的元素都加入输入栈，一旦要出队列时，我们便将输入栈的元素都反向加入输出栈，再输出
需要注意的是，如果输出栈不为空时，说明当前要输出的元素还在输出栈中，所以暂时不用将输入栈的元素加入输出栈（加入后也会导致顺序错误）
*/

public class MyQueue {
  private Stack<Integer> input; 
	private Stack<Integer> output; 
	
  /** Initialize your data structure here. */
  public MyQueue() {
    input = new Stack<Integer>(); 
		output= new Stack<Integer>();
  }
  
  /** Push element x to the back of queue. */
  public void push(int x) {
    input.push(x);  
  }
  
  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
    if (output.isEmpty()) {
			inputToOutput();
		}
    return output.pop();		
  }
  
  /** Get the front element. */
  public int peek() {
    if (output.isEmpty()) {
			while (!input.isEmpty()) {
			  inputToOutput();
			}
		}  
		return output.peek();
  }
  
  /** Returns whether the queue is empty. */
  public boolean empty() {
    return input.isEmpty() && output.isEmpty();  
  }
	
	private void inputToOutput() {
		while (!input.isEmpty()) {
			output.push((input.pop()));
		}
	}
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */ 