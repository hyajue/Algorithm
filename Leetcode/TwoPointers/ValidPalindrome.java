/**
* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
* 
* For example,
* "A man, a plan, a canal: Panama" is a palindrome.
* "race a car" is not a palindrome.
* 
* Note:
* Have you consider that the string might be empty? This is a good question to ask during an interview.
* 
* For the purpose of this problem, we define empty string as valid palindrome.
*/

/*
复杂度
时间：O(n) 空间：O(1)

思路：
从两头出发，往中间走，进行两两匹配 这里面的小问题就是在这个题目要求中，只判断字母和数字类型的字符，其他字符直接跳过即可 
因此要写一个函数判断当前字符是不是合法字符，而且因为忽略大小写，我们在判断两个字符是不是相同的时候如果是大写，要转成相应的小写字母
从两边扫描，到中间相遇，只需要一次线性扫描，复杂度是O(n)，空间上是O(1)
*/

public class ValidPalindrome {
  public boolean isPalindrome(String s) {
    if (s == null || s.length() == 0) return true;
	  int left = 0;
	  int right = s.length() - 1;
	  while (left < right) {
	    if (!isValid(s.charAt(left))) {
	  	  left++;
	  	  continue;
	    }
	    if (!isValid(s.charAt(right))) {
	  	  right--;
	  	  continue;
	    }
	    if (!isSame(s.charAt(left), s.charAt(right))) {
	  	  return false;
	    }
	    left++;
	    right--;
	  }
	  return true;
  }
	
  private boolean isValid(char c) {
	  if (c>='a' && c<='z' || c>='A' && c<='Z' || c>='0' && c<='9') { 
	    return true;	
	  }
	  return false;
  }
	
  private boolean isSame(char c1, char c2) {
    if (c1 >= 'A' && c1 <= 'Z') {
	    c1 = (char)(c1 - 'A' + 'a');
	  }
    if (c2 >= 'A' && c2 <= 'Z') {
	    c2 = (char)(c2 - 'A' + 'a'); 
	  }
    return c1 == c2;	
  }
}
 
