/**
* Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
* 
* For "(()", the longest valid parentheses substring is "()", which has length = 2.
* 
* Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

/*
solution 1 
复杂度
时间O(n) 空间O(n)

思路：dp
从后向前，一点一点计算。假设d[i]是从下标i开始到字符串结尾最长括号对长度，s[i]是字符串下标为i的括号。如果s[i-1]是左括号，
如果i + d[i] + 1是右括号的话，那d[i-1] = d[i] + 1。
如果不是则为0。如果s[i-1]是右括号，因为不可能有右括号开头的括号对，所以d[i-1] = 0。
*/

public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
	  if (s.length() < 2) return 0;
    int[] res = new int[s.length()];
    int max = 0;
    for (int i = s.length() - 2; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				int right = i + res[i+1] + 1;
				if (right < s.length() && s.charAt(right) == ')') {
					res[i] = res[i+1] + 2;
					if (right+1 < s.length()) {
						res[i] += res[right+1];
					}
				}
			}
			max = Math.max(max, res[i]);
		}
		return max; 		
	}
} 

/*
solution 2
复杂度
时间O(n)  空间O(n)

思路：stack
stack里面存放还没配好对的那些括号的index
是'(‘的时候push
是’)’的时候，说明可能配对了；看stack top是不是左括号，不是的话，push当前右括号
是的话，pop那个配对的左括号，然后update max：i和top的（最后一个配不成对的）index相减，就是i属于的这一段的当前最长。
如果一pop就整个栈空了，说明前面全配好对了，那max就是i+1
*/
public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
    int max = 0;
		if (s == null || s.length() < 2) return max;
		Stack<Integer> stack = new Stack<Integer>();
		char[] sArr = s.toCharArray();
    for (int i = 0; i < sArr.length; i++) {
			if (sArr[i] == ')' && !stack.isEmpty() && sArr[stack.peek()] == '(') {
				stack.pop();
				if (stack.isEmpty()) {
					max = i+1;
				} else {
					max = Math.max(max, i-stack.peek());
				}
			} else {
				stack.push(i);
			}
		}
    return max;		
	}
}

/*
follow up: can you do it without using extra space?
比如((()())，先遍历一遍将所有的()替换成00，得到((0000)，再遍历一遍，替换所有的(00...00)这种形式字符串为000...000，
这里我们得到(000000，直到遍历完无法替换更多括号。如果所有符号都是0，说明是有效的。这样的时间复杂度是O(N)。
*/
