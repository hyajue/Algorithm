/**
* Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
* 
* You may assume the integer do not contain any leading zero, except the number 0 itself.
* 
* The digits are stored such that the most significant digit is at the head of the list.
*/

/*
复杂度
时间:O(n) 空间O()

思路：线性扫描
maintain a carry. 
if there's carry at highest bit, then get a new array and make the highest bit 1.
One pass scan -> T(n) = O(n).   
*/

public class PlusOne {
	public int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0) {
		  return digits;
	  }
	  int carry = 1;
	  for (int i = digits.length - 1; i >= 0; i--) {
		  int digit = (digits[i] + carry) % 10;
		  carry = (digits[i] + carry) / 10;
		  digits[i] = digit;
		  if (carry == 0) {
			  return digits;
		  }
	  }
	  int[] res = new int[digits.length+1];
	  res[0] = 1;
	  return res;
	}
}