/**
* Given an integer array of size n, find all elements that appear more than n/3 times. The algorithm should run in linear time and in O(1) space.
*/

/*
复杂度
时间 O(N) 空间 O(1)

思路: 投票法
Majority Element I 中，超过一半的数只可能有一个，所以我们只要投票出一个数就行了。而这题中，超过n/3的数最多可能有两个，所以我们要记录出现最多的两个数
同样的两个candidate和对应的两个counter，如果遍历时，某个候选数和到当前数相等，则给相应计数器加1。如果两个计数器都不为0，则两个计数器都被抵消掉1
如果某个计数器为0了，则将当前数替换相应的候选数，并将计数器初始化为1。最后我们还要遍历一遍数组，确定这两个出现最多的数，是否都是众数。
*/

public class MajorityElementII {
  public List<Integer> majorityElement(int[] nums) {
    List<Integer> res = new ArrayList<Integer>();
    if(nums.length == 0 || nums == null) return res;
    int cnt1 = 0;
	int cnt2 = 0;
	int item1 = nums[0];
	int item2 = nums[0];
	for (int i = 0; i < nums.length; i++) {
	  // if nums[i] equals one of items, then increment corresponding counter
	  if (nums[i] == item1) {
		cnt1++;
	  } else if(nums[i] == item2) {
		cnt2++;
	  // if neither of items equals nums[i], decrement bother counter 
	  } else if(cnt1 != 0 && cnt2 != 0) {
		cnt1--;
		cnt2--;
	  // if one of counter is 0, then update cooresponding item
	  } else {
		  if(cnt1 == 0) {
			item1 = nums[i];
			cnt1 = 1;
		  } else {
			item2 = nums[i];
			cnt2 = 1;
		  }
	  }
	}
	cnt1 = 0;
	cnt2 = 0;
	for (int i = 0; i < nums.length; i++) {
	  if (nums[i] == item1) cnt1++;
	  if (nums[i] == item2) cnt2++;
	}
	if (cnt1 > nums.length / 3) res.add(item1);
	if (cnt2 > nums.length / 3 && item2 != item1) res.add(item2);
	return res;
  }
}

