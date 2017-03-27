/**
* Given a string s, partition s such that every substring of the partition is a palindrome.
* 
* Return all possible palindrome partitioning of s.
* 
* For example, given s = "aab",
* Return
* 
* [
*   ["aa","b"],
*   ["a","a","b"]
* ]
*/

/*
复杂度
时间 O(2^(n-1)) 空间O(n)
长度为n的字符串 有n-1个地方可以隔开 每个地方可以放挡板或者不放 -> 2^(n-1)

思路：回溯法 
N-皇后模型
*/

public class PalindromePartitioning {
  public List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<List<String>>();
    helper(res, new ArrayList<String>(), s, 0);
		return res;
  }
	private void helper(List<List<String>> res, List<String> candidates, String s, int idx) {
	  if (idx >= s.length()) {
			res.add(new ArrayList<String>(candidates));
			return;
		}
		//在循环中 递归滴处理子问题
		for(int i = idx; i < s.length(); i++) {
			String curStr = s.substring(idx, i+1);
			if (isValid(curStr)) {
			  candidates.add(curStr);
				helper(res, candidates, s, i+1);
				candidates.remove(candidates.size()-1);
			}
		}
	}
	private boolean isValid(String str) {
		if (str.length() == 1) return true;
		int lo = 0; 
		int hi = str.length() - 1;
		while (lo <= hi) {
			if (str.charAt(lo) != str.charAt(hi)) {
				return false;
			}
			lo++;
			hi--;
		}
		return true;
	}
}
