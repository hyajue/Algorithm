/**
* Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
* 
* You have the following 3 operations permitted on a word:
* 
* a) Insert a character
* b) Delete a character
* c) Replace a character
*/

/*
solution 1:
复杂度
时间：O(len1*len2) 空间O(len1*len2), where len1 = s1.length() and len2 = s2.length().

思路：dp
res[i][j] represents min edit distance from s1[0, i-1] to s2[0, j-1]
res[i][j]: Math.min{insert, delete, replace}. 
*/
 
public class EditDistance {
	public int minDistance(String word1, String word2) {
	  if (word1.length() == 0) return word2.length();
		if (word2.length() == 0) return word1.length();
		int len1 = word1.length();
		int len2 = word2.length();
		int[][] res = new int[len1+1][len2+1];
		//初始化res矩阵
		for (int i = 1; i <= len1; i++) {
		  res[i][0] = i;	
		}
		for (int i = 1; i <= len2; i++) {
			res[0][i] = i;
		}
		//按顺序填矩阵：增,删,改 三种情况取最小的作为res[i][j]
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= le2; j++) {
				int ist = res[i][j-1] + 1;
				int del = res[i-1][j] + 1;
				int rep = 0;
				if (word1.charAt(i-1) == word2.charAt(j-1)) {
					rep = res[i-1][j-1];
				} else {
					rep = res[i-1][j-1] + 1;
				}
				res[i][j] = Math.min(ist, Math.min(del, rep));
 			}
		}
		return res[len1][len2];
	}
}

/*
solution 2:
solution 1　＋ 滚动数组
*/

public class EditDistance {
	public int minDistance(String word1, String word2) {
	  if (word1.length() == 0) return word2.length();
		if (word2.length() == 0) return word1.length();
		  int len1 = words1.length();
		  int len2 = words2.length();
		  int[] res = new int[len2+1];
		  for (int j = 1; j <= len2; j++) {
			  res[j] = j; 
		  }
			for (int i = 1; i <= len1; i++) {
				for (int j = 1; j <= len2; j++) {
					int prev = res[j-1];
					int ist = res[j-1] + 1;
					int del = res[j] + 1;
					int rep = 0;
					if (word1.charAt(i) == word2.charAt(j)) {
						rep = prev;
					} else {
						rep = prev + 1;
					}
					res[j] = Math.min(ist, Math.min(del, rep));
				}
			}
	}
}









