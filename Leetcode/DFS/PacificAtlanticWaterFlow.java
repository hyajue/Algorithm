/**
* Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
* 
* Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
* 
* Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
* 
* Note:
* The order of returned grid coordinates does not matter.
* Both m and n are less than 150.
* Example:
* 
* Given the following 5x5 matrix:
* 
*   Pacific ~   ~   ~   ~   ~ 
*        ~  1   2   2   3  (5) *
*        ~  3   2   3  (4) (4) *
*        ~  2   4  (5)  3   1  *
*        ~ (6) (7)  1   4   5  *
*        ~ (5)  1   1   2   4  *
*           *   *   *   *   * Atlantic
* 
* Return:
* 
* [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
*/

/*
求所有能流向两大洋的点的集合，典型的搜索问题
从边缘当作起点开始深搜，然后标记能到达的点位true，分别标记出pacific和atlantic能到达的点，
那么最终能返回的点就是二者均为true的点
*/

public class  PacificAtlanticWaterFlow {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<int[]>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		boolean[][] pacific = new boolean[row][col];
		boolean[][] atlantic = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
			dfs(matrix, atlantic, Integer.MIN_VALUE, i, col-1);
		}
		for (int j = 0; j < col; j++) {
			dfs(matrix, pacific, Integer.MIN_VALUE, 0, j);
			dfs(matrix, atlantic, Integer.MIN_VALUE, row-1, j);
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					res.add(new int[] {i, j});
				}
			}
		}
		return res;
    }
	
	int[] dx = {0, 0, 1, -1};
	int[] dy = {1, -1, 0, 0};
	
	private void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y) {
		int row = matrix.length;
		int col = matrix[0].length;
		if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || matrix[x][y] < height) {
			return;
		}
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			dfs(matrix, visited, matrix[x][y], x + dx[i], y + dy[i]);
		}
	}
}

































 