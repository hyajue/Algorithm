/**
* Write a function to find the longest common prefix string amongst an array of strings.
*/


public class LongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strs[0].length(); i++) {
	  char c = strs[0].charAt(i);
	  for (String str : strs) {
		// 如果当前长度已经超过该串长度或者当前对比字符不匹配 则返回  
		if (str.length() < i+1 || str.charAt(i) != c) {
		  return sb.toString();
		}
	  }
		// 找到相同的字符 放入sb
		sb.append(c);
	}
    return sb.toString();    
  }
} 