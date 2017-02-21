/**
* Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
* 
* For example, given the following matrix:
* 
* 1 0 1 0 0
* 1 0 1 1 1
* 1 1 1 1 1
* 1 0 0 1 0
* Return 4.
*/
 
/*
DP: dp[i][j] stands for max side length of square with lower right coordinate (i,j)   
dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1  
*/

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
		if (row == 0) {
			return 0;
		}
		int col = matrix[0].length;
		int[][] dp = new int[row+1][col+1];
		int maxLength = 0; 
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				if (matrix[i-1][j-1] == '1') {
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
					maxLength= Math.max(maxLength, dp[i][j]);
				}
			}
		}
		return maxLength * maxLength;
    }
}


