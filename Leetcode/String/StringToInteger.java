/**
* Implement atoi to convert a string to an integer.
* 
* Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
* 
* Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
*/

/*
首先判断为空，返回0
考虑前面是空格，使用trim（）去掉，然后判断长度是否为0，是的话，返回0

判断第一个字符是不是+和-，设置变量sign记录。

循环取得字符串的数字，考虑字符串中有非数字，遇到就退出，保留前面的数字
（C语言的标准是把异常字符起的后面全部截去，保留前面的部分作为结果）

考虑溢出的情况，溢出返回Integer的最大值和最小值

这种题的考察重点并不在于问题本身，而是要注意corner case的处理
整数一般有两点需要注意，一个是正负符号问题，另一个是整数越界问题
*/

public class StringToInteger {
    public int myAtoi(String str) {
        if (str == null) {
			return 0;
		}
		// remove spaces &nbsp
		str = str.trim();
		if (str.length() == 0) {
			return 0;
		}
		int sign = 1;
		int idx = 0;
		if (str.charAt(idx) == '+') {
			idx++;
		}
		else if (str.charAt(idx) == '-') {
			idx++;
			sign = -1;
		}
		
		// get number portion. exit when it comes overflow or non-number char
		long number = 0;
		while (idx < str.length()) {
			if (str.charAt(idx) < '0' || str.charAt(idx) > '9') {
				break;
			}	
			number = number * 10 + (str.charAt(idx) - '0');
			if (number >= Integer.MAX_VALUE) {
				break;
			}
			idx++;
		}
		if (number * sign >= Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		else if (number * sign <= Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		else {
			return (int)number*sign;
		}
    }
} 















