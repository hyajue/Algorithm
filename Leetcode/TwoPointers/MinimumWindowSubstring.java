/**
* Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
* 
* For example,
* S = "ADOBECODEBANC"
* T = "ABC"
* Minimum window is "BANC".
* 
* Note:
* If there is no such window in S that covers all characters in T, return the empty string "".
* 
* If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

/*
复杂度
时间O(n) 空间O(n)

思路：
此类window题型，我们都需要用两个指针，用一个map记录字符及其出现次数，不同的是由于这里题目要求是覆盖字符串T中所有字符，所以我们需要用一个变量如count来记录window中覆盖字符串T中有效字符的个数。

只要count == T.length(), 便可更新window的最短长度。同时，我们必须移动左指针，直到window中包含的字符个数小于规定的数量，我们才开始移动右指针。另外要注意何时更新count的值。
*/

public class MinimumWindowSubstring {
	public String minWindow(String s, String t) {
		if (s == null || t == null) return null;
		if (t.length() == 0) return "";
		//build map to store frequency of chars in t
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			if (!map.containsKey(c)) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c)+1);
			}
		}
		
		int left = 0;
		int minStart = 0;
		int minLen = s.length() + 1;
		int cnt = 0;
		
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			if (map.containsKey(c)) {
				if (map.get(c) > 0) {
					cnt++;
				}
				map.put(c, map.get(c)-1);
			}
			while (cnt == t.length()) {
				if (right - left + 1 < minLen) {
					minLen = right - left + 1;
					minStart = left;
				}
				char leftChar = s.charAt(left);
				if (map.containsKey(leftChar)) {
					map.put(leftChar, map.get(leftChar)+1);
					if (map.get(leftChar) > 0) {
						cnt--;
					}
				}
				left++;
			}
		}
		if (minLen == s.length() + 1) {
			return "";
		}
		return s.substring(minStart,minStart+minLen);
	}
} 