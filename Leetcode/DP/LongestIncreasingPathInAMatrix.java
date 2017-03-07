/**
* Given an integer matrix, find the length of the longest increasing path.
* 
* From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
* 
* Example 1:
* 
* nums = [
*   [9,9,4],
*   [6,6,8],
*   [2,1,1]
* ]
* Return 4
* The longest increasing path is [1, 2, 6, 9].
* 
* Example 2:
* 
* nums = [
*   [3,4,5],
*   [3,2,6],
*   [2,2,1]
* ]
* Return 4
* The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

/*
solution 1:
记忆化搜索。
设dis[i][j]为当前点出发最大上升路径的值。初始设置为0，表示该点未知，需要更新。
再次碰到的时候只需要返回该值即可
DFS+DP - time complexity: O(mn)
*/

public class LongestIncreasingPathInAMatrix {
    private int[] dx = {1, -1, 0, 0};
	private int[] dy = {0, 0, 1, -1};
	public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] sum = new int[row][col];
		int res = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				res = Math.max(res, dfs(i, j, row, col, matrix, sum));
			}
		}
		return res;
    }
	private int dfs(int x, int y, int row, int col, int[][] matrix, int[][] sum) {
		if (sum[x][y] != 0) return sum[x][y];
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < row && ny < col && matrix[nx][ny] > matrix[x][y]) {
				sum[x][y] = Math.max(sum[x][y], dfs(nx, ny, row, col, matrix, sum));
			}
		}
		return ++sum[x][y];
	}
}