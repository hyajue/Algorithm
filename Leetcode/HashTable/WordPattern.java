/**
* Given a pattern and a string str, find if str follows the same pattern.
* 
* Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
* 
* Examples:
* pattern = "abba", str = "dog cat cat dog" should return true.
* pattern = "abba", str = "dog cat cat fish" should return false.
* pattern = "aaaa", str = "dog cat cat dog" should return false.
* pattern = "abba", str = "dog dog dog dog" should return false.
* Notes:
* You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
*/

/*
维护一个hash表 key为pattern中的字符 value为被切割的字符串
同时维护一个set 里面保存匹配过的被切割的字符串 避免重复 出现多对1的情况 
*/

public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] strArr = str.split(" ");
		if (pattern.length() != strArr.length) {
			return false;
		}
		Map<Character, String> map = new HashMap<Character, String>();
		Set<String> unique = new HashSet<String>();
		
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			if (map.containsKey(c)) {
				if (!map.get(c).equals(strArr[i])) 
					return false;
			}
				else {
					if (unique.contains(strArr[i]))
						return false;
					map.put(c, strArr[i]);
					unique.add(strArr[i]);
				}
		}
		return true;
    }
}