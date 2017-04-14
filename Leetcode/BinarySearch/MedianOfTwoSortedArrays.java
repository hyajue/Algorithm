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
			return findMedian(nums1, nums2,(len+1)/2); 
		} else {
		  double sum = findMedian(nums1, nums2, len/2) + findMedian(nums1, nums2, len/2 + 1);
			return sum / 2;
		}
	}
	private double findMedian(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
	  if (nums1.length == 0) return nums2[k-1];
		if (nums2.length == 0) return nums1[k-1];
		if (k == 1) return Math.min(nums1[0], nums2[0]);
		// always assume nums1 has less length
		if (nums1.length > nums2.length) {
		  return findMedian(nums2, start2, end2, nums1, start1, end1, k);
		}
		int len1 = Math.min(nums1.length, k/2);
		int len2 = k - idx1;
		if (nums1[len1-1] < nums2[len2-1]) {
		  return findMedian(nums1, len1, nums1.length-1, nums2, start2, end2, k - len1);
		} else if (nums1[len1-1] < nums2[len2-1]) {
			return findMedian(nums1, start1, end1, nums2, len2, end2, k - len2);
		} else {
		  return nums1[len-1];
		}
	}
} 



