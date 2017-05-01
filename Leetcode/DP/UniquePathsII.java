/**
* Follow up for "Unique Paths":
* 
* Now consider if some obstacles are added to the grids. How many unique paths would there be?
* 
* An obstacle and empty space is marked as 1 and 0 respectively in the grid.
* 
* For example,
* There is one obstacle in the middle of a 3x3 grid as illustrated below.
* 
* [
*   [0,0,0],
*   [0,1,0],
*   [0,0,0]
* ]
* The total number of unique paths is 2.
* 
* Note: m and n will be at most 100.
*/

/*
复杂度
时间：O(n^2) 空间：O(n)

思路：dp+滚动数组
res[i][j]:unique path number at grid[i][j]
sub-obtimal function:
1. if gird[i][j] == 1: res[i][j] = 0
2. else: res[i][j] = res[i][j-1] + res[i][j-1]
res[0][0] = 1;
*/

public class UniquePathsII {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
			return 0;
		// one dim dp array
		// res[i][j] = res[i-1][j] + res[i][j-1]
		int[] res = new int[obstacleGrid[0].length];
		res[0] = 1;
		for (int i = 0; i < obstacleGrid.length; i++) {
			for (int j = 0; j < obstacleGrid[0].length; j++) {
				if (obstacleGrid[i][j] == 1) {
					res[j] = 0;
				}
				else {
					if (j > 0)
						res[j] += res[j-1];
				}
			}
		}
		return res[obstacleGrid[0].length-1];
	}
} 