/**
* The count-and-say sequence is the sequence of integers beginning as follows:
* 1, 11, 21, 1211, 111221, ...
* 
* 1 is read off as "one 1" or 11.
* 11 is read off as "two 1s" or 21.
* 21 is read off as "one 2, then one 1" or 1211.
* Given an integer n, generate the nth sequence.
* 
* Note: The sequence of integers will be represented as a string.
*/

/*
复杂度
时间 O(N!) 空间 O(N) 递归栈

思路:递归 
该序列又叫做外观序列，无论如何我们都得将前一个序列元素算出来，才能计算后一个序列元素。当递归至0的时候返回初始数字1。

题意：数上次字符串中的连续出现数值的个数, 将这些字符串拼接起来

n=1时，输出字符串1；
n=2时，因为上次字符串1，1连续出现1次，就是1个1，所以输出11；
n=3时，由于上次字符串11，其中1连续出现两次，就是2个1，所以输出21；
n=4时，由于上次字符串是21，其中2出现1次，1出现1次，所以输出1211；
n=5时，由于上次字符串1211，第个1连续出现1次为11，第二个2连续出现1次为12，第三个1跟第四个1连续出现了2次，为21，所以输出111221
*/

public class CountAndSay {
  public String countAndSay(int n) {
    if (n == 0) return "";
    if (n == 1) return "1";
    String s = countAndSay(n-1);
    StringBuilder sb = new StringBuilder();
    // maintain the last char to determine if repeating 
    char last = s.charAt(0);
    int cnt = 1;
    for (int i = 1; i < s.length(); i++) {
	  // if repeat, the cnt+1
	  if (s.charAt(i) == last) {
		cnt++;
	  } else {
		// if not repeat, then add this char and reset cnt 
		sb.append(cnt);
		sb.append(last);
		last = s.charAt(i);
		cnt = 1;
	  }
	}
    // append the last char 
    sb.append(cnt);
    sb.append(last);
    return sb.toString();    
  }
}