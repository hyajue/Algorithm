/**
* Write a program to solve a Sudoku puzzle by filling the empty cells.
* 
* Empty cells are indicated by the character '.'.
* 
* You may assume that there will be only one unique solution.
*/


/*
solution 1:
复杂度
时间：O(9^81) 空间：O(1)

思路：DFS
每一步就尝试一遍所有9个数字，然后看哪个数字是可以当前合理的
*/

public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) return;
		solver(board);
	}
	
	private boolean solver(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					// try 1 to 9 for each cell 
					for (char c = '1'; c <= '9'; c++) {
						if (isValid(board, i, j, c)) {
							board[i][j] = c; //把当前这个数字放到该空格上面
							if (solver(board)) {
								return true; // 递归成功 当前找到一种可行方法 返回true
							} else {
								board[i][j] = '.'; //当前摆放方式不满足要求 回溯
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isValid(char[][] board, int i, int j, char c) {
		//check colunm 
		for (int row = 0; row < 9; row++) {
			if (board[row][j] == c) {
				return false;
			}
		}
		//check row
		for (int col = 0; col < 9; col++) {
			if (board[i][col] == c) {
				return false;
			}
		}
		//check 3x3 block 
		int rowStart = 3 * (i / 3);
		int colStart = 3 * (j / 3);
		for (int row = rowStart; row < rowStart+3; row++) {
			for (int col = colStart; col < colStart+3; col++) {
				if (board[row][col] == c) {
					return false;
				}
			}
		}
		return true;
	}
} 

/*
solution 2:
复杂度
时间：O(9^81) 空间：O(1)

思路：DFS
主体递归函数，找出当前适合的数后再递归调用。找出合适的数的方法就是遍历9个数字填充到当前位置，然后用验证函数进行验证，
然后验证通过就继续调用递归函数解出下一个位置，如果验证不通过就再尝试下一个数字。如果遍历了一遍都没有发现合适的数字，那么就返回false。
当发现所有空位都填充满了之后，就可以返回true
*/

public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) return;
		solver(board, 0, 0);
	}
	
	private boolean solver(char[][] board, int i, int j) {
		if (j >= 9) {
			// 该行已经填完 看下一行能否满足要求
			return solver(board, i+1, 0);
		}
		if (i == 9) {
			//到达第十行 证明前9行都满足要求 所有都填完了
			return true;
		}
		
		if (board[i][j] == '.') {
			for (char c = '1'; c <= '9'; c++) {
				if (isValid(board, i, j, c)) {
					board[i][j] = c;
					if (solver(board, i, j+1)) {
						return true;
					}
				}
				board[i][j] = '.';
			}
		} else {
			return solver(board, i, j+1);
		}
		return false;
	}
	
	private boolean isValid(char[][] board, int i, int j, char c) {
		//check colunm 
		for (int row = 0; row < 9; row++) {
			if (board[row][j] == c) {
				return false;
			}
		}
		//check row
		for (int col = 0; col < 9; col++) {
			if (board[i][col] == c) {
				return false;
			}
		}
		//check 3x3 block 
		int rowStart = 3 * (i / 3);
		int colStart = 3 * (j / 3);
		for (int row = rowStart; row < rowStart+3; row++) {
			for (int col = colStart; col < colStart+3; col++) {
				if (board[row][col] == c) {
					return false;
				}
			}
		}
		return true;
	}	
}
