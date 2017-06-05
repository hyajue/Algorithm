/**
* Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
* 
* For example,
* Given [3,2,1,5,6,4] and k = 2, return 5.
* 
* Note: 
* You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

/*
复杂度
时间：O(nlogK) 空间：O(K)

思路:heap
维护一个最小堆,堆中的元素大约k时,就出堆.当所有元素都过一遍堆时,堆顶元素即为第K大元素
*/

public class KthLargestElementInAnArray {
  public int findKthLargest(int[] nums, int k) {
    if (nums == null || nums.length == 0) return -1;
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    for (int i = 0; i < nums.length; i++) {
			pq.add(nums[i]);
			if (pq.size() > k) {
				pq.poll();
			}
		}
    return pq.poll;		
  }
} 

/*
复杂度
时间：O(n) in average 空间：O(1)

思路:快速选择
快排的典型应用,细节参照普林斯顿老爷爷的算法课：
http://algs4.cs.princeton.edu/lectures/23Quicksort.pdf
*/

public class KthLargestElementInAnArray {
  public int findKthLargest(int[] nums, int k) {
	  if (nums == null || nums.length == 0) return -1;
	  if (nums.length < 2) return nums[0]; 
    int lo = 0;
    int hi = nums.length - 1;
    while (lo <= hi) {
		  int idx = partition(nums, lo, hi);
		  if (idx < nums.length - k) lo = idx + 1;
		  else if (idx > nums.length - k) hi = idx - 1;
		  else return nums[idx];
		}
		return -1;
	}
	
	private int partition(int[] nums, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		int pivot = nums[lo];
		while (true) {
	    // find item on lo side to swap
			while (i < hi && nums[++i] < pivot) {
			  if (i == hi) break;				
			}
			// find item on hi side to swap
			while (nums[--j] > pivot) {
				if (j == lo) break;
			}
			//check if pointers cross
			if (i >= j) break;
			exchange(nums, i, j);
		}
		// put pivot at position j
		exchange(nums, lo, j);
		// now nums[lo...j-1] <= nums[j] <= nums[j+1...hi]
		return j;
	}
	
	private void exchange(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}