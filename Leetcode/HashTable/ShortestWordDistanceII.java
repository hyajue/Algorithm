/**
* This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?
* 
* Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
* 
* For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
* 
* Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
* 
* Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/*
复杂度
时间 O(N) 空间 O(N)

思路:哈希表法
因为会多次调用，我们不能每次调用的时候再把这两个单词的下标找出来。我们可以用一个哈希表，在传入字符串数组时，就把每个单词的下标找出存入表中。
这样当调用最短距离的方法时，我们只要遍历两个单词的下标列表就行了。具体的比较方法，则类似merge two list，每次比较两个list最小的两个值，得到一个差值。
然后把较小的那个给去掉。因为我们遍历输入数组时是从前往后的，所以下标列表也是有序的。
*/

public class ShortestWordDistanceII {
  private Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
  public WordDistance(String[] words) {
    //calculate frequency of each word 
    for (int i = 0; i　< words.length; i++) {
	  if (!map.containsKey(words[i])) {
	    List<Integer> idxList = new ArrayList<Integer>();
        idxList.add(i);		
	  } else {
		map.get(words[i]).add(i);
	  }
	}
  }
  public int shortest(String word1, String word2) {
    List<Integer> idxList1 = map.get(word1);
	List<Integer> idxList2 = map.get(word2);
	int distance = Integer.MAX_VALUE;
	int idx1 = 0;
	int idx2 = 0;
	while(idx1<idxList1.size() && idx2<idxList2.size()) {
	  distance = Math.min(distance, Math.abs(idxList1.get(idx1)-idxList2.get(idx2)));
	  if(idxList1.get(idx1) > idxList2.get(idx2)) {
	    idx2++;
	  } else {
		idx1++;
	  }
	}
	return distance;
  }
} 