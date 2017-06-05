/**
* Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
* 
* Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
* 
* The order of output does not matter.
* 
* Example 1:
* 
* Input:
* s: "cbaebabacd" p: "abc"
* 
* Output:
* [0, 6]
* 
* Explanation:
* The substring with start index = 0 is "cba", which is an anagram of "abc".
* The substring with start index = 6 is "bac", which is an anagram of "abc".
* Example 2:
* 
* Input:
* s: "abab" p: "ab"
* 
* Output:
* [0, 1, 2]
* 
* Explanation:
* The substring with start index = 0 is "ab", which is an anagram of "ab".
* The substring with start index = 1 is "ba", which is an anagram of "ab".
* The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：双指针滑动窗口

*/


public class FindAllAnagramsInAString {
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> res = new ArrayList<Integer>();
    if (s == null || s.length() == 0) return res;
    int left = 0;
    int right = 0;
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    char[] sArr = s.toCharArray();
    char[] pArr = p.toCharArray();
    for (char c : pArr) {
		  if (map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			} else {
				map.put(c, 1);
			}
		}
    while (right < sArr.length) {
      char rightC = sArr[right];
			char leftC = sArr[left];
			if (map.containsKey(rightC)) {
			  if (map.get(rightC) > 0) {
					map.put(rightC, map.get(rightC)-1);
					if (right - left + 1 == p.length()) {
						res.add(left);
						map.put(leftC, map.get(leftC)+1);
            left++;						
					}
          right++;					
				} else {
					while (leftC != rightC) {
						map.put(leftC, map.get(leftC)+1);
						left++;
						leftC = sArr[left];
					}
					left++;
					right++;
				}    	
			} else {
				while (left < right) {
					leftC = sArr[left];
					map.put(leftC, map.get(leftC)+1);
					left++;
				}
				left++;
				right++;
			}
		}
		return res;
  }
}