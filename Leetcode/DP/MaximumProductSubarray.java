/**
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

/*
solution 1:
复杂度：
时间O(n) 空间O(n)
由于乘法会出现负值 负负得正 所以需要记录最小的负值 
*/

public class MaximumProductSubarray {
  public int maxProduct(int[] nums) {
    if (nums == null || nums.length == 0) {
	  return 0;
	}
    int[] max = new int[nums.length];
    int[] min = new int[nums.length];
    max[0] = nums[0];
	min[0] = nums[0];
	int res = nums[0];
	for (int i = 1; i < nums.length; i++) {
	  if (nums[i] >= 0) {
		max[i] = Math.max(nums[i], max[i-1]*nums[i]);
		min[i] = Math.min(nums[i], min[i-1]*nums[i]);
	  } else {
		max[i] = Math.max(nums[i], min[i-1]*nums[i]);
		min[i] = Math.min(nums[i], max[i-1]*nums[i]);
	  }
	  res = Math.max(res, max[i]);
	}
	return res;
  }
}

/*
solution 2:
复杂度：
时间O(n) 空间O(1)
整体-局部最优法
*/

public class MaximumProductSubarray {
  public int maxProduct(int[] nums) {
    if (nums == null || nums.length == 0) {
	  return 0;
	}
    int localMax = nums[0];
	int localMin = nums[0];
	int globalMax = nums[0];
	for (int i = 1; i < nums.length; i++) {
	int copy = localMax;
	localMax = Math.max(Math.max(copy*nums[i], nums[i]), localMin*nums[i]);
	localMin = Math.min(Math.min(copy*nums[i], nums[i]), localMin*nums[i]);
	globalMax = Math.max(globalMax, localMax);
	}
	return globalMax;
  }
}
















