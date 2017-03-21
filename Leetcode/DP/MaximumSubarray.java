/**
* Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
* 
* For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
* the contiguous subarray [4,-1,2,1] has the largest sum = 6
*/

/*
solution 1:
复杂度：
时间O(n) 空间O(n)
*/

public class MaximumSubarray {
  public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int[] res = new int[nums.length];
    res[0] = nums[0];
    int maxSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
	  if (res[i-1] > 0) {
		res[i] = res[i-1] + nums[i];
	  } else {
		res[i] = nums[i];
	  }
	  maxSum = Math.max(maxSum, res[i]);
	}
    return maxSum;    
  }
} 

/*
solution 2:
复杂度：
时间O(n) 空间O(1)
整体-局部最优法
*/

public class MaximumSubarray {
  public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int globalMax = nums[0];
    int localMax = nums[0];
    for (int i = 1; i < nums.length; i++) {
      localMax = Math.max(localMax+nums[i], nums[i]);
	  globalMax = Math.max(globalMax, localMax);
	}
    return globalMax;    
  }
} 