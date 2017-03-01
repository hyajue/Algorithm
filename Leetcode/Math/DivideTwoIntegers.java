/**
* Divide two integers without using multiplication, division and mod operator.
* 
* If it is overflow, return MAX_INT.
*/

/*
1. 被除数/除数=商 （忽略余数）=> 被除数=除数*商。

2. 任意整数num可以表示为： num = a_0*2^0+a_1*2^1+...a_i*2^i+...+a_n*2^n.

3. 在Java中左移操作<<相当于对一个数乘以2，右移操作相当于除以2.(JAVA/C++ 右移运算带符号位->算数右移)

4.我们让除数左移直到大于被除数前的的一个数，例如计算28/3，我们进行三次左移操作，使3*2*2*2=3*8=24<28(注意四次左移操作得到3*2^4=48>28).记录下2*2*2=2^3=8.

5.我们让28减去24得到4，然后像第四步一样计算4/3,则3*2^0=3<4.记录下2^0=1.

6.由于4-3=1小于除数3，停止计算，并将每轮得到的值相加，在本例中8+1=9，记得到商（即28/3=9）。

  实现中要注意左移和求整数绝对值的边界问题

7. 将输入的Int(32位)型数字转换为long(64位)型。

8. 考虑MIN_VALUE/-1的特殊情况: -2^31 / -1 -> -2147483648 / -1 => MAX_VALUE = 2147483647 = 2^31 - 1 
*/


public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        int res = 0;
		if (divisor == 0) {  // overflow
			return Integer.MAX_VALUE;
		}
		if (dividend == Integer.MIN_VALUE && divisor == -1) { // special case
			return ~dividend;
		}
		long dividendL = Math.abs((long)dividend);
		long divisorL = Math.abs((long)divisor);
		
		while (dividendL >= divisorL) {
			int shiftNum = 0;
			while (dividendL >= (divisorL<<shiftNum)) {
				shiftNum++;
			}
			res += 1<<(shiftNum-1);
			dividendL -= divisorL<<(shiftNum-1); // update dividendL for next loop
		}
		return (dividend > 0 ^ divisor > 0) ? -res : res; 
    }
} 