/**
* Given an unsorted array nums, reorder it such that
* 
* nums[0] < nums[1] > nums[2] < nums[3]....
* 
* Example
* Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
* 
* Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
*/



public class wiggleSortII {
  public void wiggleSort(int[] nums) {
    if(nums == null || nums.length == 0) return;
	len = nums.length;
	int mid = quickSelect(nums, len / 2);
	rearrange(nums, 0, len-1, mid);
  }
  private int quickSelect(int[] nums, int k) {
    int lo = 0;
	int hi = nums.length - 1;
	while (lo < hi) {
	  int j = partition(nums, lo, hi);
	  if (j < k) lo = j + 1;
	  else if (j > k) hi = j - 1;
	  else return nums[k];
	}
	return nums[k];
  }
  private int partition(int[] nums, int lo, int hi) {
	int i = lo;
	int j = hi + 1;
	while(true) {
	  while (nums[++i] < nums[lo]) {
		if (i == hi) break;
	  }
	  while(nums[lo] < nums[--j]) {
		if (j == lo) break;
	  }
	  if (i >= j) break;
	  swap(nums, i , j);
	}
	swap(nums, lo, j); // swap with partitioning item
    return j;	
  }
  private void rearrange(int[] nums, int lo, int hi, int mid) {
    int i = lo;
	int j = hi + 1;
	while(i <= hi) {
	  if (nums[mapping(i)] > mid)  
	}
  }
  private int mapping(int index) {
	return (2*index+1) % (nums.length | 1);
  }
} 


























