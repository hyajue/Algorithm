/**
* Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
* 
* For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
*/

/*
复杂度
时间：O(1) 空间：O(1)

思路：位运算
循环中与1位与之后右移一位 然后记录1的个数  
*/

public class NumberOfOneBits {
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
	  int cnt = 0;
    while (n != 0) {
		  if ((n & 1) == 1) {
			  cnt++;
			}
      n >>>= 1; 			
		}
    return cnt;		
	}
} 
