/**
* Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
* 
* Integers in each row are sorted in ascending from left to right.
* Integers in each column are sorted in ascending from top to bottom.
* For example,
* 
* Consider the following matrix:
* 
* [
*   [1,   4,  7, 11, 15],
*   [2,   5,  8, 12, 19],
*   [3,   6,  9, 16, 22],
*   [10, 13, 14, 17, 24],
*   [18, 21, 23, 26, 30]
* ]
* Given target = 5, return true.
* 
* Given target = 20, return false.
*/

/*
复杂度
时间 O(N+M) 空间 O(1)

思路:贪心查找
由于数组的特性，我们可以从二维数组的右上角出发，如果该数比目标大，则向左移动（左边的数字肯定更小）。
如果该数比目标小，则向下移动（下边的数字肯定更大）。这样反复重复该过程就能找到目标数。
如果直到左下角都没有该数，说明找不到该数。同样的，这题也可以从左下角向右上角寻找。
*/

public class SearchA2DMatrixII {
  public boolean searchMatrix(int[][] matrix, int target) {
    // sanity check
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
    int i = 0;
    int j = matrix[0].length - 1;
    while(i < matrix.length && j >= 0) {
	  int item = matrix[i][j];
	  if (item == target) {
	    return true;
	  // move left if larger than target 
	  } else if (item > target) {
		j--;
	  // move down if smaller than target
	  } else {
		i++;
	  }
	}
	return false;
  }
} 
