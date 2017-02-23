/**
* Follow up for "Remove Duplicates":
* What if duplicates are allowed at most twice?
* 
* For example,
* Given sorted array nums = [1,1,1,2,2,3],
* 
* Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
*/

/*
maintain a counter: 
	when current item == last item, counter++
	skip current item if counter >= 3: item already repeats two times 
	reset counter = 1 if current item is different than last one
*/

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
			return 0;
		}
		int idx = 0;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i-1]) {
				count++; 
				if (count >= 3) { // repeat more than 2 times
					continue;
				}
			}
			else { // reset counter if it's a new number 
				count = 1;
			}
			// update input array and item number  
			nums[idx++] = nums[i];
		}
		return idx;
    }
} 