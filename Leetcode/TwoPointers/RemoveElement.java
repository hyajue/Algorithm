/**
* Given an array and a value, remove all instances of that value in place and return the new length.
* 
* Do not allocate extra space for another array, you must do this in place with constant memory.
* 
* The order of elements can be changed. It doesn't matter what you leave beyond the new length.
* 
* Example:
* Given input array nums = [3,2,2,3], val = 3
* 
* Your function should return length = 2, with the first two elements of nums being 2.
*/

/*
复杂度
时间O(n) 空间O(1)

思路：双指针 相对而行
一个指针从前往后走，如果遇到要删除的元素，就从队尾指针处调一个替换它。两指针相对而行
*/ 
public class RemoveElement {
  public int removeElement(int[] nums, int val) {
    if (nums == null) return 0;
		int ptr = nums.length - 1;
		for (int i = 0; i <= ptr; i++) {
			if (nums[i] == val) {
				nums[i] = nums[ptr];
				i--;
				ptr--;
			}
		}
		return ptr + 1;
  }
}