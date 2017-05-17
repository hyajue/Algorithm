/**
* Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
* 
* Note:
* Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/*
复杂度
时间 O(n) 空间O(1)

思路：位运算
写成2进制数后，对每一位求和（其实是统计每一位出现1的次数），
因而可见由重复数据求得的每一位和应该都是3的倍数，因而求余即可知道只出现1次的数据。
*/
 
public class SingleNumberII {
  public int singleNumber(int[] nums) {
    int[] bitSum = new int[32];
		int res = 0;
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < nums.length; j++) {
				bitSum[i] += (nums[j] >> i) & 1;
			}
			res |= (bitSum[i] % 3) << i;
		}
		return res;
  }
}