/**
* Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
* 
* Note: The input string may contain letters other than the parentheses ( and ).
* 
* Examples:
* "()())()" -> ["()()()", "(())()"]
* "(a)())()" -> ["(a)()()", "(a())()"]
* ")(" -> [""]
*/

/*
Solution 1: BFS + pruning 
通过从输入字符串中移除每一个括号，生成新的字符串加入队列。

如果从队列中取出的字符串是有效的，则加入结果列表。

一旦发现有效的字符串，则不再向队列中补充新的可能字符串。

根据BFS的性质，当首次从队列中发现有效字符串时，其去掉的括号数一定是最小的。

而此时，队列中存在的元素与队头元素去掉的括号数的差值 ≤ 1

并且，只有与队头元素去掉括号数目相同的元素才有可能是候选答案（根据括号匹配的性质可知）
BFS也可应用剪枝策略 记录搜过的字符串

Time complexity:

In BFS we handle the states level by level, in the worst case, we need to handle all the levels, 
we can analyze the time complexity level by level and add them up to get the final complexity.

On the first level, there's only one string which is the input string s, let's say the length of it is n, 
to check whether it's valid, we need O(n) time. On the second level, we remove one ( or ) from the first level, 
so there are C(n, n-1) new strings, each of them has n-1 characters, and for each string, we need to check whether it's valid or not, 
thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level, 
total time complexity is (n-2) x C(n, n-2), so on and so forth...

Finally we have this formula:

T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
*/

public class RemoveInvalidParentheses {
  public List<String> removeInvalidParentheses(String s) {
    List<String> res = new ArrayList<String>();
    if (s == null) return res;
    
	Set<String> visited = new HashSet<String>(); // keep tack of visited states
    Queue<String> queue = new LinkedList<String>();

    queue.offer(s);
    visited.add(s);
    boolean found = false;
    while (!queue.isEmpty()) {
	  s = queue.poll();
	  
	  if (isValid(s)) {
	    // found one valid result, put it into res
		res.add(s);
		found = true;
	  }
	  if (found) continue;
	  
	  // generate every possible cadidates to check
	  for(int i = 0; i < s.length(); i++) {
		// only remove '(' or ')'
        if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;		
	    String t = s.substring(0, i) + s.substring(i+1);
		if(!visited.contains(t)) { // for each unvisited state, add into queue
		  queue.offer(t);
		  visited.add(t);
		}
	  }
	}
	return res;	
  }
  
  // helper function to check if s contains valid parantheses
   boolean isValid(String s) {
      int cnt = 0; 
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') cnt++;
        if (c == ')') {
		  if (cnt == 0) return false;
		  cnt--;
		}
      }
      return cnt == 0;
    } 
} 