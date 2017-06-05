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

/*
复杂度
时间：O(n) 空间：O(n)

思路：哈希表法
维护一个哈希表,<key, value> = <char of s, freq of char>
然后遍历t,只要当前char c 不存在于哈希表中,就返回false;否则对应的频率减一
最后遍历哈希表,如果所有的字母对应的频率都为0,则证明s和t为同素异形词
这个方法并不受限于输入词的编码方式, unicode也可用 
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
  	  } else {
  		  sMap.put(s.charAt(i), 1);
  	  }
    }
    for (int j = 0; j < t.length(); j++) {
  	  if (!sMap.containsKey(t.charAt(j))) return false;
  	  else {
  		  sMap.put(t.charAt(j), sMap.get(t.charAt(j))-1);
  	  }
    }
    for (Character key : sMap.keySet()) {
  	  if (sMap.get(key) != 0) return false;
    }
    return true;
  }
} 

/*
复杂度
时间：O(n) 空间：O(1)

思路：数组哈希法
思路跟上面的解法类似,只是用一个长度为26的数组存储各个字母出现的频率.
该解法受限于输入字符串的编码格式.
*/

public class ValidAnagram {
  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
  	  return false;
    }
    int[] freq = new int[26];
		int idx = 0;
		for (int i = 0; i < s.length(); i++) {
			idx = s.charAt(i) - 'a';
			freq[idx]++;
		}
		for (int j = 0; j < t.length(); j++) {
			idx = t.charAt(j) - 'a';
      freq[idx]--;			
		}
		for (int k = 0; k < 26; k++) {
			if (freq[k] != 0) return false;
		}
		return true;
  }
}