/**
* Implement wildcard pattern matching with support for '?' and '*'.
* 
* '?' Matches any single character.
* '*' Matches any sequence of characters (including the empty sequence).
* 
* The matching should cover the entire input string (not partial).
* 
* The function prototype should be:
* bool isMatch(const char *s, const char *p)
* 
* Some examples:
* isMatch("aa","a") → false
* isMatch("aa","aa") → true
* isMatch("aaa","aa") → false
* isMatch("aa", "*") → true
* isMatch("aa", "a*") → true
* isMatch("ab", "?*") → true
* isMatch("aab", "c*a*b") → false
*/

/*
复杂度
时间O(n*m) 空间O(n*m)

思路：dp
类似Regular Expression Matching
res[i][j]: if s[0, i-1] matches p[0, j-1]

*/

s =  null  a   a   b  c  
    
p =  null  a   *   ?  d   		

public class WildcardMatching {
	public boolean isMatch(String s, String p) { 
	  boolean[][] res = new boolean[s.length()+1][p.length()+1];
    res[0][0] = true;
    for (int j = 0; j < p.length(); j++) {
			res[0][j+1] = res[0][j] && (p.charAt(j) == '*');
		}    
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (p.charAt(j) == '*') {
					res[i+1][j+1] = res[i+1][j] || res[i][j] || res[i][j+1];
				} else if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') {
					res[i+1][j+1] = res[i][j];
				}  
			}
		}
		return res[s.length()][p.length()];
	}
}

// another version of solution 1
public class WildcardMatching {
	public boolean isMatch(String s, String p) { 
	  boolean[][] res = new boolean[s.length()+1][p.length()+1];
		res[s.length()][p.length()] = true;
		for (int j = p.length() - 1; j >= 0; j--) {
			if (p.charAt(j) != '*') break;
			res[s.length()][j] = true;
		}
		for (int i = s.length()-1; i >= 0; i--) {
			for (int j = p.length()-1; j >= 0; j--) {
				if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') {
					res[i][j] = res[i+1][j+1];
				} else if (p.charAt(j) == '*') {
					// '*' matches with s.charAt(i) or '*' matches with ''(empty)
					res[i][j] = res[i+1][j] || res[i][j+1];
				} else {
					res[i][j] = false;
				}
			}
		}
		return res[0][0];
	}
}
	









