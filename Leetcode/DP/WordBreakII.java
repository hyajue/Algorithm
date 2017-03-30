/**
* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
* 
* Return all such possible sentences.
* 
* For example, given
* s = "catsanddog",
* dict = ["cat", "cats", "and", "sand", "dog"].
* 
* A solution is ["cats and dog", "cat sand dog"].
*/

/*
solution 1
复杂度
时间O(n*m) 空间O(n)

思路：
要返回所有可能的组合。所以现在dp[i]里面应该存可以使长度为i所有可能的String里的最后一个word。
dp有两种写法，一个就是直接写成数组：List[]的形式，不能形成的dp[i] = null。还有一个是用个hashmap：Map<Integer, List>，
用hashmap的好处是如果s很长而且用dict能组合成的长度不是很多的话，map用的空间相对少。dp结束之后，第二步就是通过dp里面保存的word，一步一步回溯找到所有结果
*/

public class WordBreakII {
	public List<String> wordBreak(String s, List<String> wordDict) {
		Map<Integer, List<String>> memo = new HashMap<Integer, List<String>>();
		memo.put(0, new ArrayList());
		memo.get(0).add("");
		
		for (int i = 1; i <= s.length(); i++) {
			for (String str : wordDict) {
				int j = i - str.length();
				if (j >= 0 && s.substring(j, i).equals(str) && memo.containsKey(j)) {
					if (!memo.containsKey(i)) {
						memo.put(i, new ArrayList());
					}
					memo.get(i).add(str);
				}
			}
		}
		List<String> res = new ArrayList<String>();
		if (!memo.containsKey(s.length())) {
			return res;
		}
		restore(res, s.length(), "", memo);
		return res;
	}
	
	private void restore(List<String> res, int idx, String curStr, Map<Integer, List<String>> memo) {
		if (idx == 0) {
			res.add(curStr);
			return;
		}
		for (String str : memo.get(idx)) {
			int j = idx - str.length();
			if (j >= 0 && memo.containsKey(j)) {
				String tmp = str + (curStr.equals("") ? "" : " " + curStr);
				restore(res, j, tmp, memo);
			}
		}
	}
} 
