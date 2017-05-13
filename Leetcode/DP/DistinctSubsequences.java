/**
* Given a string S and a string T, count the number of distinct subsequences of T in S.
* 
* A subsequence of a string is a new string which is formed from the original string 
* by deleting some (can be none) of the characters without disturbing the relative positions 
* of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
* 
* Here is an example:
* S = "rabbbit", T = "rabbit"
* 
* Return 3.
*/

/*
solution 1
复杂度
时间 O(nm) O(nm)

思路：动态规划
建立长度为m+1和n+1的二维dp数组，dp[i][j]表示S的第0到i位子串包含不同的T的第0到j位子串的个数。
初始化：当T的子串长度为0时，dp[i][0] = 1；当S的子串长度为0时，dp[0][i] = 0；当S和T子串都为0时，0包含0，故dp[0][0] = 1。
两次循环S和T，若S的最后一位和T的最后一位不同，那么S增加一位不会增加更多的包含关系，即dp[i][j]仍然等于dp[i-1][j]。
若S的最后一位和T的最后一位相等，最后的结果一定也包含S和T各减一位的结果，如S="abb"，T="ab"，所以dp[i][j]包含dp[i-1][j-1]，再与dp[i-1][j]相加。
*/

public class DistinctSubsequences {
  public int numDistinct(String s, String t) {
    if (s == null || t == null) return 0;
    int lenS = s.length();
    int lenT = t.length();
    if (lenS < lenT) return 0;
    int[][] res = new int[lenS+1][lenT+1];
    for(int i = 0; i <= lenS; i++) {
			res[i][0] = 1;
		} 
		for (int i = 1; i <= lenS; i++) {
			for (int j = 1; j <= lenT; j++) {
			  res[i][j] = res[i-1][j];
        if (s.charAt(i-1) == t.charAt(j-1)) {
					res[i][j] += res[i-1][j-1];
				}				
			}
		}
		return res[lenS][lenT];
  }
} 