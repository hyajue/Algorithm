/**
* Given an encoded string, return it's decoded string.
* 
* The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
* 
* You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
* 
* Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
* 
* Examples:
* 
* s = "3[a]2[bc]", return "aaabcbc".
* s = "3[a2[c]]", return "accaccacc".
* s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/

/*
这种括号问题肯定是用栈，最好是先在栈里存一个空元素，然后stack.peek().append()各种操作

一个栈存string, 一个栈存number， 维护一个指针numStart指向数字的开始

1. 遇到数字啥也不做

2. 遇到char： stack.peek().append(char), 然后更新numStart 

3. 遇到‘[’: stack.push(new StringBuffer()), numStack.push(number), 然后更新numStart

4. 遇到']'：str=stack.pop(), 然后numStack pop得到重复次数，按此次数append str到stack.peek()
*/

public class DecodeString {
    public String decodeString(String s) {
		StringBuffer res = new StringBuffer();
		Stack<StringBuffer> resStack = new Stack<StringBuffer>();
		Stack<Integer> numStack = new Stack<Integer>();
		resStack.push(res);
		int numStart = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (isChar(c)) {
				resStack.peek().append(c);
				numStart = i + 1;
			} else if (c == '[') {
				int num = Integer.parseInt(s.substring(numStart, i));
				numStack.push(num);
				resStack.push(new StringBuffer());
				numStart = i + 1;
			} else if (c == ']') {
				int num = numStack.pop();
				StringBuffer str = resStack.pop();
				for (int j = 0; j < num; j++) {
					resStack.peek().append(str);
				}
				numStart = i + 1;
			}
		}
		return res.toString();
    }
	private boolean isChar(char c) {
		if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') return true;
		return false;
	}
} 











