/**
* Given an unsorted integer array, find the first missing positive integer.
* 
* For example,
* Given [1,2,0] return 3,
* and [3,4,-1,1] return 2.
* 
* Your algorithm should run in O(n) time and uses constant space.
*/

/*
复杂度
时间：O(n) 空间O(1)

思路：类似index counting sort 
找第一个缺失的正整数，只要先按顺序排列好[1, 2, 3, 4, ...]，也就是nums[i] = i+1，
找到第一个和nums[i]不对应的数i+1就可以了。注意数组的index从0开始，而正整数从1开始,
所以重写排列的时候要换成index-1，而index就是从nums[0]开始的数组nums[]中的元素。

交换元素时要注意：
1. 在i处需要交换时，交换完一次后i不能移动到下一个元素，要等到换过来的元素也满足条件为止:
   swap(nums[i], nums[nums[i]-1]) until nums[i] is in place or nothing to swap
*/

 
public class FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
	  if (nums == null || nums.length == 0) return 1;
    int len = nums.length;
    for (int i = 0; i < len; i++) {
			if (nums[i] > 0 && nums[i] < len && nums[i] != nums[nums[i]-1]) {
				int tmp = nums[nums[i]-1];
				nums[nums[i]-1] = nums[i];
				nums[i] = tmp;
				i--; // note that i shuuld stay here until nums[i] is in place  
			}
		} 
    for (int idx = 0; idx < len; idx++) {
	    if (nums[idx] != idx + 1) {
				return idx + 1;
			}
    }
   	return len + 1;	
	}
}