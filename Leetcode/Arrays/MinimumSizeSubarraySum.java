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