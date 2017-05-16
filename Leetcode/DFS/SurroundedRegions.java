/**
* Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
* 
* A region is captured by flipping all 'O's into 'X's in that surrounded region.
* 
* For example,
* X X X X
* X O O X
* X X O X
* X O X X
* After running your function, the board should be:
* 
* X X X X
* X X X X
* X X X X
* X O X X
*/

/*
解法1
复杂度
时间：O(n*m) 空间：O(n+m)

思路：dfs
从一个点出发对周围区域进行目标颜色的填充,背后的思想就是把一个矩阵看成一个图的结构，每个点看成结点，而边则是他上下左右的相邻点，
然后进行一次广度或者深度优先搜索.这里选择深搜
首先根据题目要求，边缘上的'O'是不需要填充的，所以只是对上下左右边缘做染色，把所有边缘上的'O'都替换成另一个字符，比如'#'
接下来除去被换成'#'的那些顶点，剩下的所有'O'都应该被替换成'X'，而'#'那些最终应该是还原成'O'，如此可以做最后一次遍历，然后做相应的字符替换就可以了。
复杂度分析上，我们先对边缘染色，因为只有是'O'才会进行，而且会被替换成'#'，所以每个结点改变次数不会超过一次，因而是O(m*n)的复杂度，最后一次遍历同样是O(m*n)，
所以总的时间复杂度是O(m*n)。空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，同时存在的空间占用不会超过O(m+n)
*/

public class SurroundedRegions {
	public void solve(char[][] board) {
	  if (board == null || board.length <= 1 || board[0].length <= 1) return;
    int row = board.length;
    int col = board[0].length;
    //dfs处理左右边界上的'O',换成'#'
    for (int i = 0; i < row; i++) {
			if (board[i][0] == 'O') {
				fill(board, i, 0);
			}
			if (board[i][col-1] == 'O') {
				fill(board, i, col-1);
			}
		}
		// dfs处理上下边界上的'O',换成'#'
    for (int j = 0; j < col; j++) {
			if (board[0][j] == 'O') {
				fill(board, 0, j);
			}
			if (board[row-1][j] == 'O') {
				fill(board, row-1, j);
			}
		}
    // 把没有被染色的'O',换成'X'
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
 		// 把被染色成'#',换回'O'
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
	}
	
	private void fill(char[][] board, int i, int j) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') return;
		board[i][j] = '#';
		// 向上染色
		if (i - 1 >= 0 && board[i-1][j] == 'O') {
			fill(board, i-1, j);
		}
		// 向下染色
		if (i + 1 < board.length && board[i+1][j] == 'O') {
			fill(board, i+1, j);
		}
		// 向左染色
		if (j - 1 >= 0 && board[i][j-1] == 'O') {
			fill(board, i, j-1);
		}
		// 向右染色
		if (j + 1 < board[0].length && board[i][j+1] == 'O') {
			fill(board, i, j+1);
		}
	}
}

/*
解法2
复杂度同上

思路：bfs
*/

public class SurroundedRegions {
	public void solve(char[][] board) {
		if (board == null || board.length <= 1 || board[0].length <= 1) {
			return;
		}
	
		// coloring first and last rows
		for (int i = 0; i < board[0].length; i++) {
			fill(board, 0, i);
			fill(board, board.length-1, i);
		}
		// coloring first and last cols 
		for (int i = 0; i < board.length; i++) {
			fill(board, i, 0);
			fill(board, i, board[0].length-1);
		}
	
		// change all '#' to 'O', and 'O' to 'X'
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
				else if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}

	private void fill (char[][] board, int i, int j) {
		if (board[i][j] != 'O') {
			return;
		}
		board[i][j] = '#';
		// uses BFS to explore board to find all adjacent 'O'
		Queue<Integer> queue = new LinkedList<Integer>();
		// encode the matrix coordinates 
		int index = i * board[0].length + j;
		queue.offer(index);
		while (!queue.isEmpty()) {
			index = queue.poll();
			
			// get row and col coordinates from index 
			int row = index / board[0].length;
			int col = index % board[0].length;
			
			// if top item is 'O'
			if (row >= 1 && board[row-1][col] == 'O') {
				queue.offer((row-1)*board[0].length + col);
				board[row-1][col] = '#';
			}
			// if below item is 'O'
			if (row < board.length - 1 && board[row+1][col] == 'O') {
				queue.offer((row+1)*board[0].length + col);
				board[row+1][col] = '#';
			}
			// if left item is 'O'
			if (col >= 1 && board[row][col-1] == 'O') {
				queue.offer(row*board[0].length + col - 1);
				board[row][col-1] = '#';
			}
			// if right item is 'O'
			if (col < board[0].length - 1 && board[row][col+1] == 'O') {
				queue.offer(row*board[0].length + col + 1);
				board[row][col+1] = '#';
			}
		}
	}
}
