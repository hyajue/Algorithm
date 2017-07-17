/**
* Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.
* 
* Examples:
* 
* Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
* Ouptut: Sum found between indexes 2 and 4
* 
* Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
* Ouptut: Sum found between indexes 1 and 4
* 
* Input: arr[] = {1, 4}, sum = 0
* Output: No subarray found
* There may be more than one subarrays with sum as the given sum. The following solutions print first such subarray.
*/

/*
复杂度
时间：O(n) 空间：O(1)

思路：双指针
*/

public class SubarrayWithGivenSum {
	public int[] subArraySum(int[] arr, int sum) {
		int curSum = 0;
		int len = arr.length;
		int start = 0;
		
		for (int i = 0; i <　len; i++) {
   		curSum += arr[i];
			
			while (curSum > sum && start <= i - 1) {
				curSum -= arr[start];
				start++;
			}
			
			if (curSum == sum) {
				int end = i;
				return new int[] {start, end};
			}
 		}
		// if not found, return a empty array
		return new int[] {};
	}
}