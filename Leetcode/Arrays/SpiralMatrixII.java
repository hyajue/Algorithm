/**
* Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
* 
* For example,
* Given n = 3,
* 
* You should return the following matrix:
* [
*  [ 1, 2, 3 ],
*  [ 8, 9, 4 ],
*  [ 7, 6, 5 ]
* ]
*/

/*
复杂度
时间：(n^2) 空间O(1)

思路：旋转打印 
注意如果n为奇数 最后中心点不会在循环中被打印 需要在跳出循环后单独打印
*/

public class SpiralMatrixII {
	public int[][] generateMatrix(int n) {
	   if (n < 0) {
			return null;
		}    		
		int[][] res = new int[n][n];
		if (n == 0) {
			return res;
		}
		if (n == 1) {
			res[0][0] = 1;
			return res;
		}
		
		int k = 1;
		int top = 0, right = n - 1, left = 0, bottom = n - 1;
		while (left < right && top < bottom) {
			for (int j = left; j < right; j++) {
				res[left][j] = k++;
			}
			for (int i = top; i < bottom; i++) {
				res[i][right] = k++;
			}
			for (int j = right; j > left; j--) {
				res[bottom][j] = k++;
			}
			for (int i = bottom; i > top; i--) {
				res[i][left] = k++;
			}
			top++;
			left++;
			right--;
			bottom--;
		}
		if (n % 2 == 1) {
			res[n/2][n/2] = k;
		}
		return res;
	} 
} 
