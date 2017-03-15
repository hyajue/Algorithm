/**
* Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
* 
* The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

/*
复杂度
时间 O(N) 空间 O(N)

思路:stack 
栈最典型的应用就是验证配对情况，作为有效的括号，有一个右括号就必定有一个左括号在前面，所以我们可以将左括号都push进栈中，遇到右括号的时候再pop来消掉。
这里不用担心连续不同种类左括号的问题，因为有效的括号对最终还是会有紧邻的括号对。如栈中是({[，来一个]变成({，再来一个}，变成(。

注意
栈在peek或者pop操作之前要验证非空，否则会抛出StackEmptyException。
用哈希表存对应的parentheses pair
*/

public class ValidParentheses {
  public boolean isValid(String s) {
    if(s == null || s.length() == 0) return false;
    Map<Character, Character> map = new HashMap<Character, Character>();
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');
    Stack<Character> stack = new Stack<Character>();
    for (int i = 0; i < s.length(); i++) {
	  Character c = s.charAt(i);
	  switch(c) {
		case '(' : case '{' : case '[' :
          stack.push(c);
          break;
        case ')' : case '}' : case ']' :
          if(stack.isEmpty() || c != map.get(stack.pop())) {
			return false;
		  }		
	  }
	}
    return stack.isEmpty();	
  }
} 
