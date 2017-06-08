/**
* Given a string, sort it in decreasing order based on the frequency of characters.
* 
* Example 1:
* 
* Input:
* "tree"
* 
* Output:
* "eert"
* 
* Explanation:
* 'e' appears twice while 'r' and 't' both appear once.
* So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
* Example 2:
* 
* Input:
* "cccaaa"
* 
* Output:
* "cccaaa"
* 
* Explanation:
* Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
* Note that "cacaca" is incorrect, as the same characters must be together.
* Example 3:
* 
* Input:
* "Aabb"
* 
* Output:
* "bbAa"
* 
* Explanation:
* "bbaA" is also a valid answer, but "Aabb" is incorrect.
* Note that 'A' and 'a' are treated as two different characters.
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：HashMap + bucket sorting
1.建立哈希映射： <char, frequency>
2.建立桶,每个元素对应一个 frequency
3.哈希元素分配入桶.
4.倒序遍历桶(频率从大到小),顺序将每个桶的元素append到StringBuilder中
*/

public class SortCharactersByFrequency {
  public String frequencySort(String s) {
    if (s == null) return null;
    if (s.length() == 0) return "";
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    int maxFreq = 0;
    for (char c : s.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c)+1);
			}
			maxFreq = Math.max(map.get(c), maxFreq);
		}
    List<Character>[] bukit = new List[maxFreq+1];
    int cnt = 0;
    for (char c : map.keySet()) {
			int curFreq = map.get(c);
			if (bukit[curFreq] == null) {
				bukit[curFreq] = new ArrayList<Character>();
			}
			bukit[curFreq].add(c);
			cnt++;
		}
    StringBuilder sb = new StringBuilder();
    for (int i = bukit.length-1; i >= 0; i--) {
			if (bukit[i] != null) {
				for (char c : bukit[i]) {
					for (int j = 0; j < i; j++) {
						sb.append(c);
					}
				}
				if (cnt == 0) break;
			}
		}
    return sb.toString();    
  }
}