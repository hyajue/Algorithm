/**
* This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
* 
* Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
* 
* word1 and word2 may be the same and they represent two individual words in the list.
* 
* For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
* 
* Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = "makes", word2 = "makes", return 3.
* 
* Note: You may assume word1 and word2 are both in the list.
*/

/*
复杂度
时间 O(N) 空间 O(N)

思路:双指针法
这题和I是一样的，唯一不同的是对于word1和word2相同的时候，我们要区分第一次遇到和第二次遇到这个词。
引入变量times，如果是相同单词的话，每次遇到一个单词times加1，这样可以根据times来判断是否要更新distance
*/

public class ShortestWordDistanceIII {
  public int shortestWordDistance(String[] words, String word1, String word2) {
    int idx1 = -1; 
    int idx2 = -1;
    int times = 0;
    int inc = 0;
    if (word1.equals(word2)) {
	  inc = 1;
	}
    for (int i = 0; i < words.length; i++) {
	  if (words[i].equals(word1) && (times%2 == 0)) {
	    idx1 = i;
        if (idx2 != -1) {
		  distance = Math.min(distance, idx1-idx2);
		}
        times += inc;
        // note that idx2 updates regardless odd or even times is  		
	  } else if (words[i].equals(word2)) {
		idx2 = i;
		if (idx1 != -1) {
	      distance = Math.min(distance, idx2-idx1);
		}
		times += inc;
	  }
	}
    return distance;	
  }
} 
