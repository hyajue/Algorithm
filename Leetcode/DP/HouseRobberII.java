/**
* Note: This is an extension of House Robber.
* 
* After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
* 
* Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/ 

/*
DP with circle.
the first last last element cannot be present together
So we call DP twice, and return the larger result.
*/

public class HouseRobberII {
    public int rob(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		if (nums.length == 2) return Math.max(nums[0], nums[1]);
		
		return Math.max(subRubSum(0, nums.length-2, nums), subRubSum(1, nums.length-1, nums));
    }
    private int subRubSum(int start, int end, int[] nums) {
		int len = end - start + 1;
		int[] maxSum = new int[len];
		maxSum[0] = nums[start];
		maxSum[1] = Math.max(nums[start], nums[start+1]);
		for (int i = 2; i < len; i++) {
			maxSum[i] = Math.max(nums[i+start]+maxSum[i-2], maxSum[i-1]);
		}
		return maxSum[end-start];
	}
}