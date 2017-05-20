/**
* There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
* 
* For example, Given the following words in dictionary,
* 
* [
*   "wrt",
*   "wrf",
*   "er",
*   "ett",
*   "rftt"
* ] 
* The correct order is: "wertf".
* 
* Note: You may assume all letters are in lowercase. If the order is invalid, return an empty string. There may be multiple valid order of letters, return any one of them is fine.
*/

/*
复杂度
时间:O(n) 空间：O(n)

思路：topological sort
1.建图
输入单词序列已经排序,所以对于相邻的两个单词,找到第一个不相同的字符,就得到一个字符的相对顺序.
根据一系列字符的相对顺序,就可以构造图
2.DFS
有了图后,dfs,之后输出reverse dfs postorder就是topological order
*/

public class AlienDictionary {
  public String alienOrder(String[] words) {
    if (words == null || words.length == 0) return "";
    Map<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
    
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				char cur = words[i].charAt(j);
				if (!graph.containsKey(cur)) {
					graph.put(cur, new ArrayList<Character>());
				}
			}
			if (i > 0) {
				getOrder(words[i-1], words[i], graph);
			}
		}
    // use stack to get reverse dfs postorder
    Stack<Character> stack = new Stack<Character>();
    boolean[] visited = new boolean visited[26];
    boolean[] isLoop = new boolean isLoop[26];
    for (char c : graph.keySet()) {
			// if there's a cycle -> invalid input
			if (!dfs(graph, c, visited, isLoop, stack)) {
				return "";
			}
		}
    StringBuilder sb = new StringBuilder();
    while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
    return sb.toString();		
  }

  private void getOrder(String word1, String word2, Map<Character, List<Character>> graph) {
		int idx = 0;
		// find the first different character between two words
		while (idx < word1.length() && idx < word2.length() && word1.charAt(idx) == word2.charAt(idx)) {
			idx++;
		}
		if (idx < word1.length() && idx < word2.length()) {
			map.get(word1.charAt(idx)).add(word2.charAt(idx));
		}
	} 

  private boolean dfs(Map<Character, List<Character>> graph, char c, boolean[] visited, boolean[] isLoop, Stack<Character> stack) {
		int idx = c - 'a';
		if (visited[idx]) return true; //之前访问过
		if (isLoop[idx]) return false; //检测到环
		isLoop[i] = true;
		for (char nabor : graph.get(c)) {
			if (!dfs(graph, nabor, visited, isLoop, stack)) {
				return false;
			}
		}
		visited[idx] = true;
		stack.push(c);
		return true;
	}	
}