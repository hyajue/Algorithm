/**
* Implement pow(x, n).
*/

/*
把n看成是以2为基的位构成的，因此每一位是对应x的一个幂数，然后迭代直到n到最高位
比如说第一位对应x，第二位对应x*x,第三位对应x^4,...,第k位对应x^(2^(k-1)),
可以看出后面一位对应的数等于前面一位对应数的平方，所以可以进行迭代 
因为迭代次数等于n的位数，所以算法的时间复杂度是O(logn)

e.g. n = 13: 00001101, so 13 = 2^3 + 2^2 + 2^0
m^13 = m^(2^3 + 2^2 + 2^0) = m^(2^3) * m^(2^2) * m(2^0)
*/

public class Pow {
	public double myPow(double x, int n) {
	  if (n == 0) {
		  return 1.0;
	  }
	  double res = 1.0;
	  if (n < 0) { // determine if 1.0/x would cause overflow 
		  if (x >= 1.0/Double.MAX_VALUE || x <= 1.0/-Double.MAX_VALUE) {
			  x = 1.0 / x;
		  } else {
			  return Double.MAX_VALUE;
		  }
		  if (n == Integer.MIN_VALUE) {
			  res *= x;
			  n++; // to avoid overflow at n = Math.abs(n)
		  }
	  }
	  n = Math.abs(n);
	  boolean isNeg = false;
	  if (n%2==1 && x<0) {
		  isNeg = true;
	  }
	  x = Math.abs(x);
	  while (n > 0) {
		  if ((n&1) == 1) {
			  if (res > Double.MAX_VALUE/x) {
				  return Double.MAX_VALUE;
			  }
			  res *= x;
		  }
		  x *= x;
		  n = n>>1;
	  }
	  return isNeg ? -res : res;
  }
} 