/**
* Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
* 
* For example,
* Given [100, 4, 200, 1, 3, 2],
* The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
* 
* Your algorithm should run in O(n) complexity.
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：hashSet
将所有数都加入set中，然后再遍历这些数
可以一个个向上或者向下检查。为了避免之后重复检查，每查到一个数，都要将其从集合中移除。这样每遇到一个数，都检查它的上下边界，就能找出最长的连续数列。
时间复杂度是O(N)，因为我们不会检查不存在于数组的数，而存在于数组的数也只会检查一次
*/

public class LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int maxLen = 1;
		Set<Integer> set = new HashSet<Integer>();
    for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}
    for (int i = 0; i < nums.length; i++) {
			if (set.isEmpty()) break;
			int len = 1;
			int bigger = nums[i] + 1;
			while (set.contains(bigger)) {
			  len++;
        set.remove(bigger++);				
			}
			int smaller = nums[i] - 1;
			while (set.contains(smaller)) {
				len++;
				set.remove(smaller--);
			}
			if (len > maxLen) {
				maxLen = len;
			}
		}
    return maxLen;		
  }
} 
