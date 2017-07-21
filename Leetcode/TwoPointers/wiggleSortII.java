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

/*
复杂度
时间：O(n) 空间：O(1)

思路：三路快排
直观想法是找到数组的中位数，然后根据中位数对数组划分为两部分。大的那一部分放在索引为奇数的位置(1，3，5，7，9…)，
小的那一部分放在索引为偶数的位置(0，2，4，6，8…)，可以随意放 
想法对，但还不够.比如当数组为[4,5,5,6]时，得到的结果就是[4,5,5,6]，这不符合题目要求，正确的结果应该是[5,6,4,5]
这里问题出在两部分数中相同大小的数放在一起了。 
为了避免这个问题，直观上来说，需要把较小的那一部分中比较大的数和较大的那一部分中比较小的数放的越远越好
这里,三路快排的思想就起作用了.
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
	    while(i <= hi) {
	      if (nums[mapping(i)] > mid) {
					swap(nums, mapping(i), mapping(lo));
					i++;
					lo++;
				} else if (nums[mapping(i)] < mid) {
					swap(nums, mapping(i), mapping(hi));
					hi--;
				} else {
					i++;
				}      
		  }
    }
			
    private int mapping(int index) {
	    return (2*index+1) % (nums.length | 1);
    }
		
		private void swap(int[] nums, int i, int j) {
			int tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
		}
}