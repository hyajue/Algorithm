/**
* A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
* 
* The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
* 
* How many possible unique paths are there?
* 
* Above is a 3 x 7 grid. How many possible unique paths are there?
* 
* Note: m and n will be at most 100.
*/

/*
solution 1
复杂度
时间：O(nm)   空间：O(nm)

思路：dp
res[i][j]表示在(i, j)点unique path数量  
*/
public class UniquePaths {
  public int uniquePaths(int m, int n) {
    int[][] res = new int[m][n];
		for (int i = 0; i < m; i++) {		
			res[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			res[0][j] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				res[i][j] = res[i-1][j] + res[i][j-1];
			}
		}
		return res[m-1][n-1];
  }
} 

/*
solution 2
复杂度
时间：O(nm)   空间：O(n)

思路：dp
同solution 1.可以用一维数组
*/
public class UniquePaths {
  public int uniquePaths(int m, int n) {
	  int[] res = new int[n];
		res[0] = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 1; j < n; j++) {
				res[j] += res[j-1];
			}
		}
		return res[n-1];
	} 
}