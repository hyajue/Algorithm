/**
* Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
* 
* Note:
* You must not modify the array (assume the array is read only).
* You must use only constant, O(1) extra space.
* Your runtime complexity should be less than O(n2).
* There is only one duplicate number in the array, but it could be repeated more than once.
*/

/*
用二分法先选取n/2，按照抽屉原理，整个数组中如果小于等于n/2的数的数量大于n/2，说明1到n/2这个区间是肯定有重复数字的。
比如6个抽屉，如果有7个苹果要放到抽屉里，那肯定有一个抽屉至少两个苹果。这里抽屉就是1到n/2的每一个数，而苹果就是整个数组中小于等于n/2的那些数。
这样我们就能知道下次选择的数的范围，如果1到n/2区间内肯定有重复数字，则下次在1到n/2范围内找，否则在n/2到n范围内找。下次找的时候，还是找一半。

注意
我们比较的mid而不是nums[mid]
因为mid是下标，所以判断式应为cnt > mid，最后返回min
*/


public class FindTheDuplicateNumber {
  public int findDuplicate(int[] nums) {
    int min = 0;
	int max = nums.length - 1;
	while(min <= max) {
	  int mid = min + (max - min) / 2;
	  int cnt = 0;
	  for (int i = 0; i < nums.length; i++) {
	    if(nums[i] <= mid) {
			cnt++;
		}
	  }
	  if (cnt > mid) {
	    max = mid - 1;
	  } else {
		min = mid + 1;
	  }
	}
	return min;
  }
} 
