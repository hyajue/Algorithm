/**
* Given two strings s and t, write a function to determine if t is an anagram of s.
* 
* For example,
* s = "anagram", t = "nagaram", return true.
* s = "rat", t = "car", return false.
* 
* Note:
* You may assume the string contains only lowercase alphabets.
* 
* Follow up:
* What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/



public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
			return false;
		}
		Map<Character, Integer> sMap = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (sMap.containsKey(s.charAt(i))) {
				sMap.put(s.charAt(i), sMap.get(s.charAt(i))+1);
			}
			else {
				sMap.put(s.charAt(i), 1);
			}
		}
		for (int j = 0; j < t.length(); j++) {
			if (!sMap.containsKey(t.charAt(j)))
				return false;
			else {
				sMap.put(t.charAt(j), sMap.get(t.charAt(j))-1);
			}
		}
		for (Character key : sMap.keySet()) {
			if (sMap.get(key) != 0) 
				return false;
		}
		return true;
    }
} 
