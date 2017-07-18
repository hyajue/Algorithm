/**
* Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
*/

/*
复杂度
时间：O(nlogK) 空间：O(k)

思路：TreeSet
用TreeSet这个类，底层是红黑树，并有集合的性质.维护一个大小为k的TreeSet，
多余k个元素时把最早加入的给删除.用ceiling()和floor()可以找到最小的较大值和最大的较小值
*/

public class ContainsDuplicateIII {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (nums == null || nums.length == 0) return false;
    TreeSet<Integer> set = new TreeSet<Integer>();
    for (int i = 0; i < nums.length; i++) {
			int cur = nums[i];
			if (set.ceiling(cur) != null && Math.abs((long)set.ceiling(cur)-cur) <= t) return true;
			if (set.floor(cur) != null && Math.abs((long)cur-set.floor(cur)) <= t) return true;
			set.add(cur);
			if (set.size() > k) {
				set.remove(nums[i-k]);
			}
		}
    return false;		
  }
} 

/*
复杂度
时间：O(nk) 空间：O(1)

思路：滑动窗口
维护一个窗口,大小不大于k，每次来一个新数,依次跟窗口中的数字比较,看是否有满足要求的i,j
*/