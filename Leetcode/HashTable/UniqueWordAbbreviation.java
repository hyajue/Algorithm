/**
* An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
* 
* a) it                      --> it    (no abbreviation)
* 
*      1
* b) d|o|g                   --> d1g
* 
*               1    1  1
*      1---5----0----5--8
* c) i|nternationalizatio|n  --> i18n
* 
*               1
*      1---5----0
* d) l|ocalizatio|n          --> l10n
* Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
* 
* Example: 
* Given dictionary = [ "deer", "door", "cake", "card" ]
* 
* isUnique("dear") -> false
* isUnique("cart") -> true
* isUnique("cane") -> false
* isUnique("make") -> true
*/

/*
复杂度：
时间 O（n） 空间O（n）

思路：
把已有的dictionary的缩略词都缩出来，存到一个地方，再调用isUnique的时候，用目标字符串压缩后和map里面的元素做比较。
有相同的就返回false，没有就是true。
*/

public class ValidWordAbbr {  
  private map<String, List<String>> dic;
  public ValidWordAbbr(String[] dictionary) {  
    dic = new HashMap<String, List<String>>();
	for (String str : dictionary) {
	  String key = s.charAt(0) + Integer.toString(s.length()-2) + s.charAt(s.length() - 1);
	  if (dic.containsKey(key)) {
	    dic.get(key).add(str);
	  } else {
		List<String> curList = new ArrayList<String>();
		curList.add(str);
		dic.put(key, curList);
	  }
	}
  }  
  
  public boolean isUnique(String word) {  
    String key = word.charAt(0) + Integer.toString(word.length()-2) + word.charAt(word.length()-1);
	if (!dic.containsKey(key)) {
	  return true;
	} else if (dic.get(key).size() < 2 && dic.get(key).get(0).equals(word)) {
	  return true;
	} else {
	  return false;
	}
  }  
}  
  
// Your ValidWordAbbr object will be instantiated and called as such:  
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);  
// vwa.isUnique("Word");  
// vwa.isUnique("anotherWord");   