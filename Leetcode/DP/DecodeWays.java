/**
* A message containing letters from A-Z is being encoded to numbers using the following mapping:
* 
* 'A' -> 1
* 'B' -> 2
* ...
* 'Z' -> 26
* Given an encoded message containing digits, determine the total number of ways to decode it.
* 
* For example,
* Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
* 
* The number of ways decoding "12" is 2.
*/

/*
solution 1:
复杂度：
时间O(n) 空间O(n)

res[i]表示前i个字符共有多少解码可能性
1.如果第i个字符(s.charAt(i-1))可以被解码 则res[i] = res[i-1]
2.如果第i-2和第i-1个字符可以被解码，则res[i] += res[i-1]



初始化：
res[0] = 1
res[1] = 1 or 0, 需要判断第一个字符是否在‘A’到‘Z'之间
*/

LeetCode Solution
int[] -> all 0s
boolean -> all false
(i-2, i-1)
             1   3  3  0   6  4  3    2
res        1 1   2  2  0   2  
public class DecodeWays {
  public int numDecodings(String s) {
    if(s == null || s.length() == 0) return 0;
    int[] res = new int[s.length()+1];
    res[0] = 1;
    res[1] = isValid(s.substring(0,1)) ? 1 : 0;	
	for (int i = 2; i <= s.length(); i++) {
	  if (isValid(s.substring(i-1, i))) {
		res[i] += res[i-1];
	  }
	  if (isValid(s.substring(i-2, i))) {
		res[i] += res[i-2];
	  }
	}
	return res[s.length()];
  }
  private boolean isValid(String str) {
	if (str == null || str.length() == 0) return false;
	if (str.charAt(0) == '0') return false;
	int num = Integer.parseInt(str);
	return num >= 1 && num <= 26;
  }
} 