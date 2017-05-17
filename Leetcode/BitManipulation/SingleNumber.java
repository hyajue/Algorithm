/**
* Given an array of integers, every element appears twice except for one. Find that single one.
* 
* Note:
* Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/*
复杂度
时间：O(n) 空间:O(1)

思路：位运算
将每个整数看成二进制.如果某个数出现两次,那每一位也出现两次.这样把所有数每一位都按位相加,不考虑进位,则最后
的结果即为出现一次的数字
*/

public class SingleNumber {
  public int singleNumber(int[] nums) {
    int[] digits = new int[32];
  for (int i = 0; i < 32; i++) {
  	for (int j = 0; j < nums.length; j++) {
  		digits[i] += (nums[j] >> i) & 1;
  	}
  }
  int res = 0;
  for (int i = 0; i < 32; i++) {
  	res += (digits[i] % 2) << i; 
  }
  return res;
  }
} 