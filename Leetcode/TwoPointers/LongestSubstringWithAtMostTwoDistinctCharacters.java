/**
* Given a string, find the length of the longest substring T that contains at most k distinct characters.
* 
* For example, Given s = “eceba” and k = 2,
* 
* T is "ece" which its length is 3.
*/

/*
这种类型的题都应该用两个指针解决，同时用一个map来记录字符及其出现次数。一个右指针先移动，不断更新map, 
当发现map里的字符个数大于规定个数的时候，开始移动左指针，同时更新map,直到map里的字符个数等于规定个数，
中间不断更新包含规定字符个数的最大长度。
time: O(n), space: O(n)
*/

public class LongestSubstringWithAtMostTwoDistinctCharacters {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    if(s == null || s.length() == 0) return 0;
	Map<Character, Integer> map = new HashMap<Character, Integer>();
	int left = 0;
	int maxLen = 0;
	for (int i = 0; i < s.length(); i++) {
	  //update map based on current pointer i
	  char c = s.charAt(i);
	  if (!map.containsKey(c)) {
	    map.put(c, 1);
	  } else {
		  map.put(c, map.get(c)+1);
	  }
	  
	  // move left pointer until number of Character in map is less or equal to k
	  while (map.size() > k) {
	    char leftChar = s.charAt(left);
		if (map.containsKey(leftChar)) {
		  // note that there may have duplicatins, so decrease the freq of this Char
		  // to zero before remove this Char 
		  map.put(leftChar, map.get(leftChar)-1);
		  if (map.get(leftChar) == 0) {
			map.remove(leftChar);
		  }
		}
		left++;
	  }
	  maxLen = Math.max(maxLen, i-left+1);
	}
	return maxLen;
  }
}