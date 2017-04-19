/**
* Given an array of size n, find the majority element. The majority element is the element that appears more than n/2 times.
* 
* You may assume that the array is non-empty and the majority element always exist in the array.
*/

/*
复杂度
时间 O(n) 空间O(n)

思路：
维护一个HashMap, 每次遇到元素就加入该表， 再判断出现次数如果大于(nums.length)/2，就输出该元素
*/

public class MajorityElement {
	public int majorityElement(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int res = 0;
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
			} else {
				map.put(num, map.get(num)+1);
			}
			if (map.get(num) > nums.length/2) {
				res = num;
				break;
			}
		}
		return res;
	}
} 