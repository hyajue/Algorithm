/**
* Reverse digits of an integer.
* 
* Example1: x = 123, return 321
* Example2: x = -123, return -321
* 
* The input is assumed to be a 32-bit signed integer. 
* Your function should return 0 when the reversed integer overflows.
*/

/*
复杂度
时间O(n) 空间O(1)

思路
通过对数字模十取余得到它的最低位->注意考虑溢出问题->用long

follow up:
Q：拿到反转整数题目后第一步是什么？ A：先问出题者尾部有0的数字反转后应该是什么形式，其次问清楚溢出时应该返回什么。

Q：除了检查溢出返回特定值以外，有没有别的方法处理溢出？ A：可以使用try-catch代码块排除异常。
*/

public class ReverseInteger {
	public int reverse(int x) {
	  long res = 0;
    long xL = Math.abs((long)x);
		while (xL > 0) {
		  res *= 10;
			res += xL%10;
			if (res > Integer.MAX_VALUE) {
			  return 0;
			}
			xL /= 10;
		}
		return (int) (x > 0 ? res : -res);
	}
} 
