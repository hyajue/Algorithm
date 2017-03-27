/**
* The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
* 
* Given an integer n, return all distinct solutions to the n-queens puzzle.
* 
* Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
* 
* For example,
* There exist two distinct solutions to the 4-queens puzzle:
* 
* [
*  [".Q..",  // Solution 1
*   "...Q",
*   "Q...",
*   "..Q."],
* 
*  ["..Q.",  // Solution 2
*   "Q...",
*   "...Q",
*   ".Q.."]
* ]
*/

/*
复杂度：
时间：NP 指数级别 空间O(n)

思路：递归+回溯
用一个循环递归处理子问题。这个问题中，在每一层递归函数中，我们用一个循环把一个皇后填入对应行的某一列中，
如果当前棋盘合法，我们就递归处理下一行，找到正确的棋盘我们就存储到结果集里面

这类题目都是这个套路，用一个循环去枚举当前所有情况，然后把元素加入，递归，再把元素移除，

这道题目中不用移除的原因是这里用一个一维数组去存皇后在对应行的哪一列，因为一行只能有一个皇后，
如果用二维数组，那么就需要把那一行那一列在递归结束后设回没有皇后，所以道理是一样的。

注意
这道题最后一个细节就是怎么实现检查当前棋盘合法性的问题，因为除了刚加进来的那个皇后，前面都是合法的，
我们只需要检查当前行和前面行是否冲突即可。检查是否同列很简单，检查对角线就是行的差和列的差的绝对值不要相等就可以
*/

public class NQueens {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> res = new ArrayList<List<String>>();
    helper(0, n, new ArrayList<Integer>(), res);    
  }
  private void helper(int row, int n, List<Integer> candidates, List<List<String>> res) {
    if (row == n) {
	  if (isValid(candidates)) {
	    List<String> curRes = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
		  StringBuilder sb = new StringBuilder();
		  for (int j = 0; j < n; j++) {
		    if (j == candidates.get(i)) {
			  sb.append('Q');  
			} else {
			  sb.append('.');
			}
		  }
		  curRes.add(sb.toString());
		}
		res.add(curRes);
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




















