/**
* Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
* 
* For example,
* Given the following matrix:
* 
* [
*  [ 1, 2, 3 ],
*  [ 4, 5, 6 ],
*  [ 7, 8, 9 ]
* ]
* You should return [1,2,3,6,9,8,7,4,5].
*/

/*
复杂度
时间:O(nm)  空间O(1)

思路：顺序展开 
维护四个变量 row, col, x, y
按照"右 下 左 上"的顺序展开原矩阵 每经过一圈展开 更新四个变量:x和y各加1，row和col各减2
如果只剩下一行或者一列 直接把该行/列加入结果集 
*/

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
	  if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
    int row = matrix.length;
    int col = matrix[0].length;
    int x = 0; 
    int y = 0;

    while (row > 0 && col > 0) {
			// if one row/col left, just add items into List res
      if (row == 1) {
			  for (int j = 0; j < col; j++) {
					res.add(matrix[x][y++]);
				}
				break;
			}
      if (col == 1) {
				for (int i = 0; i < row; i++) {
					res.add(matrix[x++][y]);
				}
			  break;  
			}
      // below process is a circle 
      // from top to right
      for (int i = 0; i < col - 1; i++) {
				res.add(matrix[x][y++]);
			}
      // from right to down 
      for (int i = 0; i < row - 1; i++) {
				res.add(matrix[x++][y]);
			}
      // from down to left 
      for (int i = 0; i < col - 1; i++) {
				res.add(matrix[x][y--]);
			}
      // from left to top
      for (int i = 0; i < row - 1; i++) {
				res.add(matrix[x--][y]);
			}
      x++;
      y++;
      row -= 2;
      col -= 2;	 		
		}
    return res;		
	}
}
