/**
* Given an array of non-negative integers, you are initially positioned at the first index of the array.
* 
* Each element in the array represents your maximum jump length at that position.
* 
* Determine if you are able to reach the last index.
* 
* For example:
* A = [2,3,1,1,4], return true.
* 
* A = [3,2,1,0,4], return false.
*/

/*
复杂度
时间

思路：贪心
维护一个最大距离，遍历数组的过程中 如果当前idx加上当前最远距离大于最大距离，则更新最大距离

注意for循环中的条件里的：i<=max:如果当前idx都跳不到 后面的也就不用遍历了  
*/

public class JumpGame {
	public boolean canJump(int[] nums) { 
	  if (nums == null || nums.length == 0) return false;
		int max = 0;
		for (int i = 0; i < nums.length && i <= max; i++) {
		  max = Math.max(max, nums[i] + i);
		}
		return max >= nums.length - 1 ? true : false;
	}
}