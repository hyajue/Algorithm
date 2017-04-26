/*
* Given an array of non-negative integers, you are initially positioned at the first index of the array.
* 
* Each element in the array represents your maximum jump length at that position.
* 
* Your goal is to reach the last index in the minimum number of jumps.
* 
* For example:
* Given array A = [2,3,1,1,4]
* 
* The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
* 
* Note:
* You can assume that you can always reach the last index.
*/

/*
复杂度
时间O(n^2) 空间O(1)

思路：双指针
要计算最短的步数，就不能贪心每次都找最远距离了，因为有可能一开始跳的远的路径，后面反而更慢。
所以要探索所有的可能性.维护一个窗口，计算在这一步下能达到的最远距离，不断移动这个窗口.移动过程中计数，
记录需要移动几次，才能覆盖末尾的值
*/

public class JumpGameII {
	public int jump(int[] nums) {
	  if (nums == null || nums.length == 0) return 0;
    int cnt = 0;
    int start = 0;
    int end = 0;
    int preEnd = 0;
    while (end < nums.length - 1) {
		  cnt++;
			preEnd = end;
			for (int i = start; i <= preEnd; i++) {
			  end = Math.max(end, nums[i] + i);   
			}
			start = preEnd + 1;
		}
		return cnt; 		
	}
}