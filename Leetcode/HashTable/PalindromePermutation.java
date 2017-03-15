/**
* Given a string, determine if a permutation of the string could form a palindrome.
* 
* For example, "code" -> False, "aab" -> True, "carerac" -> True.
* 
* Hint:
* 
* Consider the palindromes of odd vs even length. What difference do you notice? Count the frequency of each character. If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

/*
复杂度
时间 O(N) 空间 O(N)

考察一下回文的性质，回文中除了中心对称点的字符，其他字符都会出现偶数次。而中心对称点如果是字符，该字符会是奇数次，
如果在两个字符之间，则所有字符都是出现偶数次。所以，我们只要判断下字符串中每个字符出现的次数，就知道该字符串的其他排列方式中是否有回文了。
*/

public class PalindromePermutation {
  public boolean canPermutePalindrome(String s) {
    if (s == null || s.length() == 0) return false;
	Map<Character, Integer> map = new HashMap<Character, Integer>();
	// calculate frequency of each char 
	for (int i = 0; i < s.length(); i++) {
	  char c = s.charAt(i);
	  if (!map.containsKey(c)) {
	    map.put(c, 1);
	  } else {
		map.put(c, map.get(c)+1);
	  }
  }
  // find if there's more than one frequency > 1
  boolean Odd = false;
  for (Integer num : map.values()) {
    if (num % 2 == 1) { 
	  if (!Odd) {
		Odd = true;
	  } else {
        return false;   
	  }
	}	  
  }
  return true;
}