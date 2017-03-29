/**
* Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
* 
* Do not allocate extra space for another array, you must do this in place with constant memory.
* 
* For example,
* Given input array nums = [1,1,2],
* 
* Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
*/

/*
复杂度
时间O(n) 空间O(1)

思路：双指针
维护两个指针，一个保留当前有效元素的长度，一个从前往后扫，然后跳过那些重复的元素。
因为数组有序，所以重复元素一定相邻，不需要额外记录 

follow up: what if the array is unordered? -> use extra data structure to keep 
					 track of item appeared. 
*/

public class RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) return 0;      
		int idx = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i-1]) {
				nums[idx] = nums[i];
				idx++;
			}
		}
		return idx;
	}
} 
