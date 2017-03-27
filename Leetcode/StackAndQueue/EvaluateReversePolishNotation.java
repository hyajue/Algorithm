/**
* Evaluate the value of an arithmetic expression in Reverse Polish Notation.
* 
* Valid operators are +, -, *, /. Each operand may be an integer or another expression.
* 
* Some examples:
*   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
*   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

/*
复杂度
时间 O(n) 空间O(n)

思路：栈
遇到数字就压栈 遇到运算符就弹出栈顶的两个数字作为运算数进行运算 临时结果继续压栈 
重复上述过程 直到遍历完整个tokens 最后栈顶的元素就是返回值
*/

public class EvaluateReversePolishNotation {
  public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<Integer>();
    for (String token : tokens) {
			switch(token) {
				case "+" :
					stack.push(stack.pop() + stack.pop());
					break;
				case "-" :
					stack.push(-stack.pop() + stack.pop());
				  break;
				case "*" :
					stack.push(stack.pop() * stack.pop());
					break;
				case "/" :
					int denom = stack.pop();
					int numer = stack.pop();
					stack.push(numer / denom);
					break;
				default: 
					stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
  }
}	
