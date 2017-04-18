/**
* Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
* 
* For example, given n = 3, a solution set is:
* 
* [
*   "((()))",
*   "(()())",
*   "(())()",
*   "()(())",
*   "()()()"
* ]
*/

/*
复杂度
时间O(n) 空间O(n)->栈空间

思路：回溯法
放置一个新的符号时，我们有两个选择，放置左括号，或者放置右括号，但是按照题意我们最多放置n个左括号，放一个剩余可放置左括号就少一个，
但剩余可放置右括号则多了一个。而对于右括号，必须前面放了一个左括号，我们才能放一个右括号。所以我们根据剩余可放置左括号，和剩余可放置右括号，代入递归求解。
*/

public class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
	  List<String> res = new ArrayList<String>();
		if (n <= 0) return res;
    helper(res, n, 0, new StringBuilder());
		return res;
	}
	
	private void helper(List<String> res, int left, int right, StringBuilder tmp) {
	  // both left parentheses and right parentheses are placed  
		if (left == 0 && right == 0) {
			res.add(tmp.toString());
			return;
		}
    if (left > 0) {
			helper(res, left - 1, right + 1, tmp.append("("));
			tmp.deleteCharAt(tmp.length()-1);
		} 
    if (right > 0) {
			helper(res, left, right - 1, tmp.append(")"));
			tmp.deleteCharAt(tmp.length()-1);
		}  	
	} 
} 
