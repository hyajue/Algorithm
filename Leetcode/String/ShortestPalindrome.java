/**
* Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
* 
* For example:
* 
* Given "aacecaaa", return "aaacecaaa".
* 
* Given "abcd", return "dcbabcd".
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：kmp+反转拼接
1.整体反转原字符串,设为revS，并将revS拼接到s之后,设整体字符串为comb
2.对comb建立next数组
3.根据next数组的性质,revS从最后一位向前数next[len(comb)]位,为公共的部分,所以不必要加到s前面
4.将revS切除掉3中不必要的部分,拼接到s前面,返回
*/

public class ShortestPalindrome {
  public String shortestPalindrome(String s) {
    if (s == null || s.length() == 0) return s;
    String revS = (new StringBuilder(s)).reverse().toString();
    String comb = s + "$" + revS;
    int len = comb.length();
		int[] next = new int[len+1];
		// call helper to contruct next array
		getNext(comb, next);
		int cutLen = next[len];
		String afterCut = revS.substring(0, revS.length()-cutLen);
		System.out.println(cutLen);
		if (cutLen != s.length()) {
		return afterCut + s;
		} else {
			// original s already a palindrome 
		  return s; 
		}
		
  }
	
	private void getNext(String comb, int[] next) {
		int i = 1;
		int j = 0;
		int len = comb.length();
		while (i < len) {
			if (comb.charAt(i) == comb.charAt(j)) {
				next[++i] = ++j;				
			} else if (j == 0) {
				i++;
			} else {
				j = next[j];
			}
		}
	}
} 
