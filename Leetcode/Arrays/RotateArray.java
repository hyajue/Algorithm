/**
* Rotate an array of n elements to the right by k steps.
* 
* For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
* 
* Note:
* Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
*/

/*
复杂度
时间 O(N) 空间 O(1)

思路
通过三次反转，我们可以很巧妙的实现旋转数组。首先我们将整个数组反转，然后将前k个数字反转，然后再将后面剩下的数字反转，就得到目标数组了。

注意
反转数组最简单的方法是交换元素，而交换元素有至少三种方法（临时变量，相加相减，异或）
k可能大于数组长度，旋转不止一次，所以我们要先对k取余

follow up: 
如果是向左旋转k位而不是向右呢？
还是同样的方法，只是之前在反转完整个数组后，将其前k个单独反转，后面的单独反转。而左旋时，我们是将其后k个单独反转，然后前面的单独反转。
*/

public class RotateArray {
  public void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k == 0) return;
    k %= nums.length; // k may be larger that the length of nums
    reverse(nums, 0, nums.length-1);
    reverse(nums, 0, k-1);
    reverse(nums, k, nums.length-1);	
  }
  private void reverse(int[] nums, int i, int j) {
	while (i < j) {
	  swap(nums, i, j);
	  i++;
	  j--;
	}
  }
  private void swap(int[] nums, int i, int j) {
	int tmp = nums[i];
	nums[i] = nums[j];
	nums[j] = tmp;
  }
}
