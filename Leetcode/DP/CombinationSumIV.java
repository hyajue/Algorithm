/**
* Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
* 
* Example:
* 
* nums = [1, 2, 3]
* target = 4
* 
* The possible combination ways are:
* (1, 1, 1, 1)
* (1, 1, 2)
* (1, 2, 1)
* (1, 3)
* (2, 1, 1)
* (2, 2)
* (3, 1)
* 
* Note that different sequences are counted as different combinations.
* 
* Therefore the output is 7.
* Follow up:
* What if negative numbers are allowed in the given array?
* How does it change the problem?
* What limitation we need to add to the question to allow negative numbers?
*/

/*
DP两种思路：
dp[i] += dp[i-num]
dp[i+num] += dp[i]
如果允许输入数组中存在负数，则结果可能存在无数种情况。比如输入数组中存在-1，正数加完后可以用-1各种减回去
可以加入一些限制条件使得输出收敛，比如：最多取N个数，负数只能用N次，每个数只能用N次等等。
*/

public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        if (target < 0) {
			return 0;
		}
		int[] res = new int[target+1];
		for (int i = 0; i <= target; i++) {
			for (int k = 0; k < nums.length; k++) {
				if (i - nums[k] > 0) {
					res[i] += res[i-nums[k]];
				}
				else if (i - nums[k] == 0) {
					res[i] += 1;
				}
			}
		}
		return res[target];
    }
}

 
