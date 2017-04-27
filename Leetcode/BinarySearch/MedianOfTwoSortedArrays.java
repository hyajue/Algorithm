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

对于这两个数组，设我们的函数是find(A, B, K),

首先，分别取A[K/2-1] 和B[K - (K)/2 -1]比较(记K-K/2为K')，分如下三种情况：

A[K/2-1] < B[K'-1], 则A[0...(K)/2-1]的所有值都可以忽略了，因为：

假设不能忽略，即A∪B[K-1]就在A[0...K/2-1]里面，取最大值A[K/2-1]为A∪B[K-1]。
由此则B中必定有K'个元素小于A[K/2-1]，而又因为A[K/2-1] < B[K'-1]，故假设不成立。
A[K/2-1] > B[K'-1]，同理B[0...K'-1]的值均可忽略了。
A[K/2-1] = B[K'-1]，这就是要找的值了，终止。
假设发生了情况1(情况2也类似)，则我们可以知道：

A∪B[K-1]一定在A(K/2-1...len(A))或B中了，因为步骤1已经去掉了的(K-1)/2个元素（注：它们不是最小元素的，但的确小于A∪B[K-1]），于是现在应该是寻找下标大小是第K'的元素了。
设A = A[(K/2)..len(A)), B = B, K = K - K/2
递归到第一步，当其中一个数组长度为0，则另一个取下标K-1即可;另一种情况是，当K为1时，取min(A[0], B[0])
*/

public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0 && nums2.length == 0) return 0;
		int len1 = nums1.length;
		int len2 = nums2.length;
    int len = len1 + len2;
		if (len % 2 == 1) {
			return findMedian(nums1, 0, len1-1, nums2, 0, len2-1, (len+1)/2); 
		} else {
		  double sum1 = findMedian(nums1, 0, len1-1, nums2, 0, len2-1, len/2);
		  double sum2 = findMedian(nums1, 0, len1-1, nums2, 0, len2-1, len/2 + 1);
		  double sum = sum1 + sum2;
			return sum / 2;
		}
	}
	
	private double findMedian(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
		if (start1 > end1) return nums2[start2 + k-1];
		if (start2 > end2) return nums1[start1 + k-1];
		if (k == 1) return Math.min(nums1[start1], nums2[start2]);
		// always assume len1 <= len2. If not, then switch position of nums1 and nums2
		if (nums1.length > nums2.length) {
		  return findMedian(nums2, start2, end2, nums1, start1, end1, k);
		}
		int len1 = Math.min(end1-start1+1, k/2);
		int len2 = k - len1;
		if (nums1[start1+len1-1] < nums2[start2+len2-1]) {
		  return findMedian(nums1, start1+len1, end1, nums2, start2, end2, k - len1);
		} else if (nums1[start1+len1-1] > nums2[start2+len2-1]) {
			return findMedian(nums1, start1, end1, nums2, start2+len2, end2, k - len2);
		} else {
		  return nums1[start1+len1-1];
		}
	}
} 



