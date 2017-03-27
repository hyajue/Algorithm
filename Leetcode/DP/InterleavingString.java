/**
* Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
* 
* For example,
* Given:
* s1 = "aabcc",
* s2 = "dbbca",
* 
* When s3 = "aadbbcbcac", return true.
* When s3 = "aadbbbaccc", return false.
*/

/*
复杂度
时间：O(nm) 空间：(nm)

思路：动态规划
状态定义：dp[i][j] s1取前i个字符 s2取前j个字符 s3取前(i+j)字符 是否能匹配
状态转移方程:如果最后一位(i+j位)与s1的最后一位(i位)相等 dp[i][j] = dp[i-1][j]
与s2最后一位相等则dp[i][j] = dp[i][j-1]
初始化: dp[0][0] = true dp[i][0] dp[0][j]看s1 s2 s3比较
返回: dp[s1.length][s2.length]
*/
 
public class InterleavingString {
	public boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) return false;
		
		boolean[][] res = new boolean[s1.length()+1][s2.length()+1];
		res[0][0] = true;
		for (int i = 0; i < s1.length(); i++) {
		  if (s1.charAt(i) == s3.charAt(i) && res[i][0] == true) {
			  res[i+1][0] = true;
			}
		}
		for (int j = 0; j < s2.length(); j++) {
			if (s2.charAt(j) == s3.charAt(j) && res[0][j] == true) {
				res[0][j+1] = true;
			} 
		}
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s3.charAt(j+i-1) == s1.charAt(i-1) && res[i-1][j]) {
					res[i][j] = true;
				}
				if (s3.charAt(j+i-1) == s2.charAt(j-1) && res[i][j-1]) {
					res[i][j] = true;
				}
			}
		}
		return res[s1.length()][s2.length()];
	}
}
