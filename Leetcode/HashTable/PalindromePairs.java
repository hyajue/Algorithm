/**
* Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
* 
* Example 1:
* Given words = ["bat", "tab", "cat"]
* Return [[0, 1], [1, 0]]
* The palindromes are ["battab", "tabbat"]
* Example 2:
* Given words = ["abcd", "dcba", "lls", "s", "sssll"]
* Return [[0, 1], [1, 0], [3, 2], [2, 4]]
* The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
*/

/*
利用字典map保存单词与下标的键值对；

1）若当前的单词word本身是回文，且words中存在空串，则将空串下标bidx与idx加入答案；

2）若当前单词的逆序串在words中，则将逆序串下标rids与idx加入答案；

3）将当前单词word拆分为左右两半left，right

3.1）若left为回文，并且right的逆序在words中，则将right的逆序串下标rridx与idx加入答案；

3.2）若right为回文，并且left的逆序串在words中，则将left的逆序串下标idx与rlidx加入答案。

例如：”sssll” = “ss” + “sll”，而且数组中存在”lls”，因此组合”lls”和”sssll”。
注意：组合两个字符串时，需要注意顺序问题。如果字符串前半段为回文，则另一个字符串需要组合在前面；如果字符串后半段为回文，则另一个字符串需要组合在后面。
*/


public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) 
			return null;
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], i);
		}
		
		for (int i = 0; i < words.length; i++) {
			int left = 0;
			int right = 0;
			while (left <= right) {
				String substr = words[i].substring(left, right);
				Integer j = map.get(new StringBuilder(substr).reverse().toString());
				// deal with right part 
				if (j != null && i != j && left == 0 && isValid(words[i].substring(right, words[i].length()))) {
					List<Integer> curList = new ArrayList<Integer>();
					curList.add(i);
					curList.add(j);
					res.add(curList);
				}
				else {
					// deal with left part 
					if (j != null && i != j && left != 0 && isValid(words[i].substring(0,left))) {
					List<Integer> curList = new ArrayList<Integer>();
					curList.add(j);
					curList.add(i);
					res.add(curList);						
					}
				}
				// move left or right pointer
				if (right == words[i].length()) {
					left++;
				}
				else {
					right++;
				}
			}
		}
		return res;
    }
	// helper method to determine if input str is Palindrome
	private boolean isValid(String str) {
		for (int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length()-i-1)) {
				return false;
			}
		}
		return true;
	}
} 



































