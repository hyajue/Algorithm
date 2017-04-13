/**
* There are two sorted arrays nums1 and nums2 of size m and n respectively.
* 
* Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
* 
* Example 1:
* nums1 = [1, 3]
* nums2 = [2]
* 
* The median is 2.0
* Example 2:
* nums1 = [1, 2]
* nums2 = [3, 4]
* 
* The median is (2 + 3)/2 = 2.5
*/

/*
复杂度
时间:O(log(n+m)) 空间:O(1)

思路:分治
要求O(log(m+n))时间复杂度，首先想分治法。这类问题可以总结为"Top K问题"

*/

public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0 && nums2.length == 0) return 0;
		int len = nums1.length + nums2.length;
		if (len % 2 == 1) {
			return findMedian(nums1, nums2,)
		}
	}
} 
