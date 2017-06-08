/**
* Given two strings s and t, determine if they are isomorphic.
* 
* Two strings are isomorphic if the characters in s can be replaced to get t.
* 
* All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
* 
* For example,
* Given "egg", "add", return true.
* 
* Given "foo", "bar", return false.
* 
* Given "paper", "title", return true.
* 
* Note:
* You may assume both s and t have the same length.
*/


public class IsomorphicStrings {
  public boolean isIsomorphic(String s, String t) {
    if (s == null && t == null) {  // both null strings
  	  return true;
    }
    if (s == null || t == null) {  // one of them is null
    	return false;
    }
    if (s.length() == 0 && t.length() == 0) { // both zero length
    	return true;
    }
    if (s.length() != t.length()) {  // lengths not equal
    	return false;
    }
  
    Map<Character, Character> map = new HashMap<Character, Character>();
    Set<Character> set = new HashSet<Character>(t.length());
    char sChar;
    char tChar;
    for (int i = 0; i < s.length(); i++) {
    	sChar = s.charAt(i);
    	tChar = t.charAt(i);
  	
  	  // if key not present in map, then keep key-value pair
  	  if (!map.containsKey(sChar)) {
  	  	if (set.contains(tChar)) { // muti-key to one value case
  	  		return false;
  	  	} else {
  	  		map.put(sChar, tChar);
  	  		set.add(tChar);
  	  	}
  	  } else { // key already exists
  	      if (map.get(sChar) != tChar) {  // one key to multi-value case
  	  		return false;
  	  	}    	
  	  }
    }
    return true;
  }
}