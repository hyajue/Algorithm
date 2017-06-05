/**
* Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
* 
* Solve it without division and in O(n).
* 
* For example, given [1,2,3,4], return [24,12,8,6].
* 
* Follow up:
* Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
*/

/*
T(n) = O(n); S(n) = O(1)  

first pass to calculate res[i]: the product from [0->i)

second pass to calculate res[i]:the product from (i->nums.length-1] 

maintain one array res[] and two vairiables to store product left/right 
*/

public class ProductOfArrayExceptSelf {
  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length == 0) return nums;
    int len = nums.length;
    int[] res = new int[len];
    int left = 1;
    for (int i = 0; i < len; i++) {
	    res[i] = left;
	    left *= nums[i];
	  }
    int right = 1;
    for (int i = len-1; i >= 0; i--) {
	    res[i] *= right;
      right *= nums[i];	  
	  }
    return res;    
  }
} 
