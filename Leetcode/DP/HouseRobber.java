/**
* You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
* 
* Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/

/*
DP: global-local max method 
*/

public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int[] globalMax = new int[nums.length];
		int[] localMax = new int[nums.length];
		localMax[0] = nums[0];
		globalMax[0] = nums[0];
		globalMax[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i++) {
			localMax[i] = nums[i] + globalMax[i-2];
			globalMax[i] = Math.max(globalMax[i-1], localMax[i]);
		}
		return globalMax[nums.length-1];
    }
}





























 



