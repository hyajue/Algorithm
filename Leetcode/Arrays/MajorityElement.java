/**
* Given an array of size n, find the majority element. The majority element is the element that appears more than n/2 times.
* 
* You may assume that the array is non-empty and the majority element always exist in the array.
*/

/*
维护一个HashMap，它直接判断次数如果大于nums.length，就输出该元素
*/

public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int res = 0;
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
			}
			else {
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