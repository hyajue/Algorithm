/**
* Given a set of distinct integers, nums, return all possible subsets.
* 
* Note: The solution set must not contain duplicate subsets.
* 
* For example,
* If nums = [1,2,3], a solution is:
* 
* [
*   [3],
*   [1],
*   [2],
*   [1,2,3],
*   [1,3],
*   [2,3],
*   [1,2],
*   []
* ]
*/

/*
classic NP problem
*/

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
			return null;
		}
		Arrays.sort(nums);
		return findSubSets(nums, nums.length-1);
    }
	private List<List<Integer>> findSubSets(int[] nums, int index) {
		if (index == -1) {
			List<List<Integer>> res = new ArrayList<List<Integer>>();
			List<Intger> element = new ArrayList<Integer>();
			res.add(element);
			return res;
		}
		List<List<Integer>> res = findSubSets(nums, index-1);
		int size = res.size();
		for (int i = 0; i < size; i++) {
			List<Integer> element = new ArrayList<Integer>(res.get(i));
			element.add(nums[index]);
			res.add(element);
		}
		return res;
	}
}
































 
