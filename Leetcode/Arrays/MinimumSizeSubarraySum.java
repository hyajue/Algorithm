/**
* Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
* 
* For example, given the array [2,3,1,2,4,3] and s = 7,
* the subarray [4,3] has the minimal length under the problem constraint.
* 
* click to show more practice.
* 
* More practice:
* If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

/*
复杂度
时间：O(n) 空间O(1)

思路：双指针
维护一个窗口,使得窗口中元素的和小于s
当元素和小于s的时候,移动右指针并把当前数加入当前和,当加完之后大于等于s时,就移动左指针,将当前数字减去.每次移动左指针之前,都更新最小距离.
*/
 
public class MinimumSizeSubarraySum {
  public int minSubArrayLen(int s, int[] nums) {
    int start = 0;
    int end = 0;
    int sum = 0;
    int min = Integer.MAX_VALUE;
  
    while (start < nums.length && end < nums.length) {
    	while (sum < s && end < nums.length) {
    		sum += nums[end++];
    	}
    	while (sum >= s && start <= end) {
    		min = Math.min(min, end-start);
    		sum -= nums[start++];
    	}
    }
    return min == Integer.MAX_VALUE ? 0 : min;
  }
}