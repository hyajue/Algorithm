/**
* Determine if a Sudoku is valid.
* The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
* 
* Note:
* A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/

/*
solution 1
复杂度
时间O(1) 空间O(1)

思路：哈希表存储出现过的数字

how to think about % and / in line 56 and 60. These two operators can be helpful for matrix traversal problems.

For a block traversal, it goes the following way.

0,0, 0,1, 0,2; < --- 3 Horizontal Steps followed by 1 Vertical step to next level.

1,0, 1,1, 1,2; < --- 3 Horizontal Steps followed by 1 Vertical step to next level.

2,0, 2,1, 2,2; < --- 3 Horizontal Steps.

And so on...
But, the j iterates from 0 to 9.

But we need to stop after 3 horizontal steps, and go down 1 step vertical.

Use % for horizontal traversal. Because % increments by 1 for each j : 0%3 = 0 , 1%3 = 1, 2%3 = 2, and resets back. So this covers horizontal traversal for each block by 3 steps.

Use / for vertical traversal. Because / increments by 1 after every 3 j: 0/3 = 0; 1/3 = 0; 2/3 =0; 3/3 = 1.

So far, for a given block, you can traverse the whole block using just j.

But because j is just 0 to 9, it will stay only first block. But to increment block, use i. To move horizontally to next block, use % again : ColIndex = 3 * i%3 (Multiply by 3 so that the next block is after 3 columns. Ie 0,0 is start of first block, second block is 0,3 (not 0,1);

Similarly, to move to next block vertically, use / and multiply by 3 as explained above.  
*/

public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
	  for (int i = 0; i < 9; i++) {
			Set<Character> rows = new HashSet<Character>();
			Set<Character> cols = new HashSet<Character>();
			Set<Character> blks = new HashSet<Character>();
			
			for (int j = 0; j < 9; j++) {
				//行出现重复
				if (board[i][j] != '.' && !rows.add(board[i][j])) {
					return false;
				}
				//列出现重复
				if (board[j][i] != '.' && !cols.add(board[j][i])) {
					return false;
				}
				
				int rowIdx = 3 * (i / 3);
				int colIdx = 3 * (i % 3);
				//blocks出现重复
				if (board[rowIdx+j/3][colIdx+j%3] != '.' && !blks.add(board[rowIdx+j/3][colIdx+j%3])) {
					return false;
				}
			}
		}
		return true;
	}
} 

/*
solution 2
复杂度
时间O(1) 空间O(1)

思路：
两个for循环遍历矩阵中所有的字符，若不是'.'且不满足数独规则isvalid()，则返回false。
对于给定坐标的字符，写一个数独规则的函数isvalid()判读在某些范围内是否有重复值出现，先比较行，再比较列，最后比较每个小九方格。
*/

public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.' && !isValid(board, i, j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isValid(char[][] board, int row, int col) {
		for (int i = 0; i <　9; i++) {
			if (board[row][col] == board[i][col] && row != i) {
				return false;
			}
		}
		for (int j = 0; j < 9; j++) {
			if (board[row][col] == board[row][j] && col != j) {
				return false;
			}
		}
		int rowStart = 3*(row/3);
		int colStart = 3*(col/3);
		for (int i = rowStart; i < rowStart+3; i++) {
			for (int j = colStart; j < colStart+3; j++) {
				if (board[i][j] == board[row][col] && !(row == i && col == j)) {
					return false;
				}
			}
		}
		return true;
	}
}