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

/*
复杂度
时间 O(n) 空间O(1)

思路：
维护一个candidate变量，还有一个counter变量，
如果新数和candidate一样，那么counter加上1，否则的话，如果counter不是0，则counter减去1，
如果counter已经是0，则将candidate更新为当前遍历到的新数
因为每一对不一样的数都会互相消去，最后留下来的candidate就是众数
*/

public class MajorityElement {
  public int majorityElement(int[] nums) {
    int candidate = nums[0];
		int cnt = 0;
    for(int i = 1; i < nums.length; i++){
      if(candidate == nums[i]){
        cnt++;
      } else if(cnt==0){
        candidate = nums[i];
      } else {
        cnt--;
      }
    }
    return candidate;
  }
}