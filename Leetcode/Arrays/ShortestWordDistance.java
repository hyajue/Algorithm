/**
* Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
* 
* For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
* 
* Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
* 
* Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/*
复杂度
时间：O(n) 空间：O(1)

思路：双指针
一个指针指向word1上次出现的位置，一个指针指向word2上次出现的位置。因为两个单词如果比较接近的话，肯定是相邻的word1和word2的位置之差，
故每次得到一个新位置就和和另一个单词的位置比较一下
*/

public class ShortestWordDistance {
  public int shortestDistance(String[] words, String word1, String word2) { 
    int idx1 = -1;
		int idx2 = -1;
		int dist = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
		  if (words[i].equals(word1)) {
        idx1 = i;		
			  if (idx2 != -1) {
			  	dist = Math.min(dist, idx1 - idx2);
			  }
			}
			if (words[i].equals(word2)) {
			  idx2 = i;
        if (idx1 != -1) {
          dist = Math.min(dist, idx2 - idx1);
				}				
			}
		}
		return dist;
  }
}