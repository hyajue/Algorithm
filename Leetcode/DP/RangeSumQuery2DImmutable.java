/** 
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 * 
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

/* dp -> many calls to sumRegion function
   maintain sumRecod[i][j]: sum from [0][0] to [i][j]   
   +-----+-+-------+     +--------+-----+     +-----+---------+     +-----+--------+
   |     | |       |     |        |     |     |     |         |     |     |        |
   |     | |       |     |        |     |     |     |         |     |     |        |
   +-----+-+       |     +--------+     |     |     |         |     +-----+        |
   |     | |       |  =  |              |  +  |     |         |  -  |              |
   +-----+-+       |     |              |     +-----+         |     |              |
   |               |     |              |     |               |     |              |
   |               |     |              |     |               |     |              |
   +---------------+     +--------------+     +---------------+     +--------------+
   
    sumRecord[i][j]   =  sumRecord[i-1][j]  + sumRecord[i][j-1]  - sumRecord[i-1][j-1] +  
                         matrix[i-1][j-1]	
  T(n) = O(n^2), S(n) = O(n).						 
*/
public class NumMatrix {
	private int [][] sumMatrix;
	private int row;
	private int col;
	boolean noVal = false;
	
	public NumMatrix(int[][] matrix) {
		this.row = matrix.length;
		if (this.row == 0) {
			noVal = true;
			return;
		}
		this.col = matrix[0].length;
		if (this.col == 0) {
			noVal = true;
			return;
		}
		sumMatrix = new int[row+1][col+1];
		
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				sumMatrix[i][j] = sumMatrix[i-1][j] + sumMatrix[i][j-1] - sumMatrix[i-1][j-1] + matrix[i-1][j-1];
			}
		}	
	} 
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (noVal) {
			return 0;
		}
		else {
			return sumMatrix[row2+1][col2+1] - sumMatrix[row1][col2+1] - sumMatrix[row2+1][col1] + sumMatrix[row1][col1];    
		}
	}	
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
 

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 