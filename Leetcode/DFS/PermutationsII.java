/**
* Given a collection of numbers that might contain duplicates, return all possible unique permutations.
* 
* For example,
* [1,1,2] have the following unique permutations:
* [
*   [1,1,2],
*   [1,2,1],
*   [2,1,1]
* ]
*/

/*
复杂度
时间:NP 指数级 空间O(n)

思路：回溯法
与PermutationsI类似的模板 只是需要考虑去重问题，所以做以下处理：
1.对输入数组进行排序 确保重复元素相邻
2.循环递归开始时要判断是否重复
*/

public class PermutationsII {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (nums == null || nums.length == 0) return res;
    Arrays.sort(nums);	
	  helper(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
	  return res;
  }
  
	private void helper(List<List<Integer>> res, List<Integer> curList, int[] nums, boolean[] used) {
	  if (curList.size() == nums.length) {
	    res.add(new ArrayList<Integer>(curList));
	    return;
	  } else {
	    for (int i = 0; i < nums.length; i++) {
		    if (i > 0 && used[i-1] && nums[i] == nums[i-1]) continue;
		    if (!used[i]) {
		      used[i] = true;
		      curList.add(nums[i]);
		      helper(res, curList, nums, used);
		      curList.remove(curList.size()-1);
		      used[i] = false;
		    }
	    }
	  }
  }
} 