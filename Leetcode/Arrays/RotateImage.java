/**
* You are given an n x n 2D matrix representing an image.
* 
* Rotate the image by 90 degrees (clockwise).
* 
* Follow up:
* Could you do this in-place?
*/

/*
solution 1
复杂度
时间O(n^2) 空间O(n)

思路：转置镜像法
step 1: transpose the matrix: matrix[i][j] <-> matrix[j][i](转置)
step 2: flip each row of transposed matrix(镜像)
*/

public class RotateImage {
	public void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return;
		}
		int rowNum = matrix.length;
		int colNum = rowNum;
		// transpose the matrix
		for (int i = 0; i < rowNum; i++) {
			for (int j = i + 1; j < colNum; j++) {
				swap(i, j, matrix);
			}
		}
		// flip each row of transposed matrix
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < (colNum >> 1); j++) {
				flip(i, j, matrix);
			}
		}
	}
	
	private void swap(int i, int j, int[][] matrix) {
		int tmp = matrix[i][j];
		matrix[i][j] = matrix[j][i];
		matrix[j][i] = tmp;
	}
	
	private void flip(int i, int j, int[][] matrix) {
		int colNum = matrix[0].length;
		int tmp = matrix[i][j];
		matrix[i][j] = matrix[i][colNum-j-1];
		matrix[i][colNum-j-1] = tmp;
	}
} 