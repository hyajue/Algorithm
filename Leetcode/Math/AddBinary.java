/**
* Given two binary strings, return their sum (also a binary string).
* 
* For example,
* a = "11"
* b = "1"
* Return "100".
*/

/*
从低位开始，一直相加并且维护进位。和Add Two Numbers的区别是这个题目低位在后面，所以要从string的尾部往前加。
时间复杂度是O(max(m,n))，m和n分别是两个字符串的长度，空间复杂度是结果的长度O(max(m,n))
维护的res是把低位放在前面，为了满足结果最后要进行一次reverse
*/

public class AddBinary {
  public String addBinary(String a, String b) {
    if (a == null || a.length() == 0) return b;
    if (b == null || b.length() == 0) return a;
    int i = a.length() - 1;
    int j = b.length() - 1;
    int carry = 0;
    StringBuilder res = new StringBuilder();
    while(i >= 0 && j >= 0) {
	  int digit = (a.charAt(i) - '0' + b.charAt(j) - '0') + carry;
	  carry = digit / 2;
	  digit %= 2;
	  res.append(digit);
	  i--;
	  j--;
	}
    while (i >= 0) {
	  int digit = (a.charAt(i) - '0') + carry;
	  carry = digit / 2;
	  digit %= 2;
	  res.append(digit);
	  i--;
	}
    while (j >= 0) {
	  int digit = (b.charAt(j) - '0') + carry;
	  carry = digit / 2;
	  digit %= 2;
	  res.append(digit);
	  j--;
	}
    if (carry > 0) {
	  res.append(carry);
	}
    return res.reverse().toString();	
  }
}