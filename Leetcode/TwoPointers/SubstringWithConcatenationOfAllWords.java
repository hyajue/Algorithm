/**
* You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
* 
* For example, given:
* s: "barfoodbarthefoobarman"
* words: ["foo", "bar"]
* 
* You should return the indices: [0,9].
* (order does not matter).
*/

/*
复杂度
时间：O(n) 空间O(n)

思路：双指针(滑动窗口)
维护一个窗口 窗口里面是字典里出现的词 同max length of substring套路一样 只不过这里是去匹配一个字符串
*/

public class SubstringWithConcatenationOfAllWords {
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> res = new ArrayList<Integer>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			if (map.containsKey(words[i]) {
				map.put(words[i], map.get(words[i])+1);
			} else {
				map.put(words[i], 1);
			}
		}
		int wordLen = words[0].length();
		for (int i = 0; i < wordLen; i++) {
			Map<String, Integer> curMap = new HashMap<String, Integer>();
			int cnt = 0; //窗口中的单词数
			int left = i; //窗口左指针
			for (int j = i; j < s.length() - wordLen; j += wordLen) {
				String str = s.substring(j, j+wordLen);
				if (map.containKey(str)) {
					if (curMap.containsKey(str)) {
						curMap.put(str, curMap.get(str)+1);
					} else {
						curMap.put(str, 1);
					}
					cnt++; //增加窗口内单词数
					while (curMap.get(str) > map.get(str)) {
						String tmp = s.substring(left, left+wordLen);
						curMap.put(tmp, curMap.get(tmp)-1);
						left += wordLen;
						cnt--;
					}
					if (cnt == words.length) {
						res.add(left);
						String tmp = s.substring(left, left+wordLen);
						curMap.put(tmp, curMap.get(tmp)-1);
						left += wordLen;
						cnt--;
					}
				} else { // search miss 
					cnt = 0;
					curMap.clear();
					left = j + wordLen;
				}
			}
		}
		return res;
  }
}
 