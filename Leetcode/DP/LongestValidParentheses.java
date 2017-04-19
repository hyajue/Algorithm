/**
* Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
* 
* For "(()", the longest valid parentheses substring is "()", which has length = 2.
* 
* Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

/*
复杂度
时间O(n) 空间O(n)

思路：dp

*/

public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
	  if (s.length() < 2) return 0;
    int[] res = new int[s.length()];
    int max = 0;
    for (int i = s.length() - 2; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				int right = i + res[i+1] + 1;
				if (right < s.length() && s.charAt(i) == ')') {
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
