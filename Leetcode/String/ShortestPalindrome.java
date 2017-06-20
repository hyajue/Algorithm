/**
* Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
* 
* For example:
* 
* Given "aacecaaa", return "aaacecaaa".
* 
* Given "abcd", return "dcbabcd".
*/



public class ShortestPalindrome {
  public String shortestPalindrome(String s) {
    if (s == null || s.length() == 0) return s;
    String revS = (new StringBuilder(s)).reverse().toString();
    String comb = s + "$" + revS;
    int len = comb.length();
		int[] next = new int[len+1];
		getNext(comb, next);
		int cutIdx = next[len];
		String afterCut = revS.substring(0, curIdx);
		return afterCut + s;
		
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
