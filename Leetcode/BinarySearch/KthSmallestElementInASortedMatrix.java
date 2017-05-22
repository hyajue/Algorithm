/**
* Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
* 
* Note that it is the kth smallest element in the sorted order, not the kth distinct element.
* 
* Example:
* 
* matrix = [
*    [ 1,  5,  9],
*    [10, 11, 13],
*    [12, 13, 15]
* ],
* k = 8,
* 
* return 13.
* Note: 
* You may assume k is always valid, 1 ≤ k ≤ n2.
*/



public class KthSmallestElementInASortedMatrix {
  public int kthSmallest(int[][] matrix, int k) {
    int row = matrix.length;
    int left = matrix[0][0];
    int right = matrix[row-1][row-1];
    while (left <= right) {
			int mid = left + (right - left) / 2;
			int num = cnt(matrix, mid);
		}		
  }
	
	private int cnt(int[][] matrix, int target) {
		int row = matrix.length;
		int res = 0;
		int i = row - 1;
		int j = 0;
		while (i >= 0 && j < row) {
			if (matrix[i][j] < target) {
				res += i + 1;
				j++;
			} else {
				i--;
			} 
		}
		return res;
	}
}
 

