/**
* Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
* 
* For example,
* If n = 4 and k = 2, a solution is:
* 
* [
*   [2,4],
*   [3,4],
*   [2,3],
*   [1,2],
*   [1,3],
*   [1,4],
* ]
*/

/*
复杂度
时间： NP问题 指数级 空间O(n)

思路:回溯法
N皇后套路 用循环递归地去处理子问题
*/

public class Combinations {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if ( n <= 0 || n < k) return res;
	  helper(1, n, k, new ArrayList<Integer>(), res);
	  return res;
  }
  
	private void helper(int start, int n, int k, List<Integer> curList, List<List<Integer>> res) {
	  if (curList.size() == k) {
	    res.add(new ArrayList<Integer>(curList));
	    return;
	  } else {
	    for (int i = start; i <= n; i++) {
		    curList.add(i);
		    helper(i+1, n, k, curList, res);
		    curList.remove(curList.size()-1);
	    }
	  }
  }
} 
