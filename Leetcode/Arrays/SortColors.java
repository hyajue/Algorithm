/**
* Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
* 
* Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
* 
* Note:
* You are not suppose to use the library's sort function for this problem.
* 
* Follow up:
* A rather straight forward solution is a two-pass algorithm using counting sort.
* First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
* 
* Could you come up with an one-pass algorithm using only constant space?
*/

/*
Using 3-way quick sort to achieve one-pass algo
pick '1' as pivot:
[less than 1, ==1, bigger than 1] 

index counting sort is also a solution, which can be used in many other questions.
*/

public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
			return;
		}
		int i = 0; 				// nums[0...<i) == 0
		int j = 0; 				// nums[i...<j) == 1
		int k = nums.length; 	// nums[k...<n) == 2
		int tmp = 0;
		while (j < k) {
			if (nums[j] == 1) {
				j++;
			}
			else if (nums[j] == 0) {
				tmp = nums[j];
				nums[j++] = nums[i];
				nums[i++] = tmp;
			}
			else { // num[j] == 2
				tmp = nums[j];
				nums[j] = nums[k-1];
				nums[k-1] = tmp;
				k--;
			}
		}
		return;
    }
}