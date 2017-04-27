/**
* Follow up for N-Queens problem.
* 
* Now, instead outputting board configurations, return the total number of distinct solutions.
*/

/*
复杂度：
时间： NP 指数级 空间O(n)

思路：
跟N-Queens算法是完全一样的，只是把输出从原来的结果集变为返回结果数量而已
*/

public class NQueensII {
  public int totalNQueens(int n) {
    List<Integer> res = new ArrayList<Integer>();
    res.add(0);
    helper(0, n, new ArrayList<Integer>(), res);
    return res.get(0);	
  }
	
  private void helper(int row, int n, List<Integer> candidates, List<Integer> res) {
    if (row == n) {
	    if (isValid(candidates)) {
        res.set(0, res.get(0)+1);
	    }
      return;	  
	  } else {
	    for (int j = 0; j < n; j++) {
	      candidates.add(j);
		    if (isValid(candidates)) {  //剪枝 不符合要求的摆放方法直接跳过
		      helper(row+1, n, candidates, res);        
		    }
	      candidates.remove(candidates.size()-1);
	    }  
	  }
  }
	
  private boolean isValid(List<Integer> list) {
    int row = list.size() - 1;
    int col = list.get(row);
    for (int i = 0; i <= row - 1; i++) {
	    int otherRow = i;
	    int otherCol = list.get(i);
	    if (col == otherCol) return false;
	    // check对角线是否共线 
	    if (Math.abs(row-otherRow) == Math.abs(col-otherCol)) return false;
	  }	
	  return true;
  }
} 