/**
* Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
* 
* For example, given nums = [-2, 0, 1, 3], and target = 2.
* 
* Return 2. Because there are two triplets which sums are less than 2:
* 
* [-2, 0, 1]
* [-2, 0, 3]
* Follow up:
* Could you solve it in O(n2) runtime?
*/

 

public class ThreeSumSmaller {
  public int threeSumSmaller(int[] nums, int target) {
    if (nums == null || nums.length == 0) return 0;
	Arrays.sort(nums); 
	int len = nums.length;
	int cnt = 0;
	
	for (int k = 0; k < len-2; k++) {
	  int i = k + 1;
      int j = len - 1;
      while (i < j) {
		int sum = nums[k] + nums[i] + nums[j];
		if (sum >= target) {
		  j--;
		} else {
		    cnt += j - i;
			i++;
		}
	  }	  
	}
	return cnt;
  }
}
