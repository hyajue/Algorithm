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
复杂度
时间：O(2^n) 空间：O(2^n) 

思路：
N皇后类型.循环中递归求解
注意给定集合里面可能会有重复元素,所以在结果集中要进行查重处理：
1.首先对输入数组进行排序,方便后面查重
2.在某一层递归中,如果前后元素相等,则跳过后面的元素
*/

public class SubsetsII {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    if (nums == null) return null;
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    findSubSets(nums, 0, new ArrayList<Integer>(), res);
		return res;
  }
	
	private void findSubSets(int[] nums, int index, List<Integer> tmp, List<List<Integer>> res) {
		if (index >= nums.length) {
			res.add(new ArrayList<Integer>(tmp));
		}
		res.add(new ArrayList<Integer>(tmp));
		for (int i = index; i < nums.length; i++) {
			if (i != index && nums[i] == nums[i-1]) {
				continue;
			}
			tmp.add(nums[i]);
			findSubSets(nums, i+1, tmp, res);
			tmp.remove(tmp.size()-1);
		}
	}
} 