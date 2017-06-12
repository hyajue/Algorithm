/**
* Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
* 
* Example 1:
* Input: "abab"
* 
* Output: True
* 
* Explanation: It's the substring "ab" twice.
* Example 2:
* Input: "aba"
* 
* Output: False
* Example 3:
* Input: "abcabcabcabc"
* 
* Output: True
* 
* Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

/*
复杂度
时间：O(n^2) 空间：O(n)

思路：
挨个试,尝试的长度从[1,len/2]
*/

public class RepeatedSubstringPattern {
  public boolean repeatedSubstringPattern(String s) {
    int len = s.length();
    for (int i = len/2; i > 0; i--) {
			if (len % i == 0) {
				int parts = len / i;
				String sub = s.substring(0, i);
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < parts; j++) {
					sb.append(sub);
				}
				if (sb.toString().equals(s)) return true;
			}
		}
    return false;    
  }
}

/*
复杂度
时间：O(n) 空间：O(n)

*/