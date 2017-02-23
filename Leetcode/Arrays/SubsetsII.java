/**
* Given a collection of integers that might contain duplicates, nums, return all possible subsets.
* 
* Note: The solution set must not contain duplicate subsets.
* 
* For example,
* If nums = [1,2,2], a solution is:
* 
* [
*   [2],
*   [1],
*   [1,2,2],
*   [2,2],
*   [1,2],
*   []
* ]
*/

/*
classic NP problem: find all subset 

*/

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) {
			return null;
		}
		Arrays.sort(nums);
		List<Integer> lastSet = new ArrayList<Integer>();
		lastSet.add(0);
		return findSubSets(nums, nums.length-1, lastSet);
    }
	private List<List<Integer>> findSubSets(int[] nums, int index, List<Integer> lastSet) {
		if (index == -1) {
			List<List<Integer>> res = new ArrayList<List<Integer>>();
			List<Integer> element = new ArrayList<Integer>();
			res.add(element);
			return res;
		}
		List<List<Integer>> res = findSubSets(nums, index-1, lastSet);
		int size = res.size();
		int start = 0;
		if (index > 0 && nums[index] == nums[index-1]) {
			start = lastSet.get(0);
		}
		for (int i = start; i < size; i++) {
			List<Integer> element = new ArrayList<Integer>(res.get(i));
			element.add(nums[index]);
			res.add(element);
		}
		lastSet.set(0, size);
		return res;
	}
} 