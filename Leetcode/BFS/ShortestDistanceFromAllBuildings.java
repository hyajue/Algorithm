/**
* You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
* 
* Each 0 marks an empty land which you can pass by freely.
* Each 1 marks a building which you cannot pass through.
* Each 2 marks an obstacle which you cannot pass through.
* For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
* 
* 1 - 0 - 2 - 0 - 1
* |   |   |   |   |
* 0 - 0 - 0 - 0 - 0
* |   |   |   |   |
* 0 - 0 - 1 - 0 - 0
* The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
* 
* Note:
* There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/*
复杂度
时间： O(nm*B) 空间：O(nm) B = number of buildings

思路：bfs
从building出发进行BFS，本题需要返回最小距离和，所以维护一个二维矩阵,里面存每个点到所有building的距离和，
每次BFS，都更新相应的距离和。最后遍历那个距离和矩阵，找出最小值即可

这道题还有个条件是empty room必须reach all buildings，所以再维护另一个矩阵存对应empty room到building的个数，如果最终个数不等于总的building数，对应点存的距离和无效。
*/

public class ShortestDistanceFromAllBuildings {
  public int shortestDistance(int[][] grid) { 
    if (grid == null || grid.length == 0) return -1;
    int row = grid.length;
    int col = grid[0].length;
    
    // maintain distance from each grid to each building 
    int[][] dist = new int[row][col];
    // maintain number of buildings that each grid can reach 
    int[][] num = new int[row][col];
    int buildingNum = 0;
    
		//bfs from each of buildings 
    for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {
					buildingNum++;
					bfs(grid, i, j, dist, num);
				}
			}
		}
    int minDist = Integer.MIN_VALUE;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 0 && dist[i][j] != 0 && num[i][j] == buildingNum) {
					minDist = Math.min(minDist, dist[i][j]);
				}
			}
		}
   	if (minDist <= Integer.MAX_VALUE) return minDist;
    return -1;		
  }
	
	private void bfs(int[][] grid, int row, int col, int[][] dist, int[][] num) {
	  int row = grid.length;
    int col = grid[0].length;
    
    Queue<int[]> queue = new LinkedList<int[]>();
    queue.add(new int[]{row, col});
    int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    boolean[][] visited = new boolean[row][col];
    int level = 0;
    while (!queue.isEmpty()) {
			level++;
			int qLen = queue.size();
			for (int i = 0; i < qLen; i++) {
				int[] corr = queue.poll;
				for (int j = 0; j < dirs.length; j++) {
					int x = corr[0] + dirs[j][0];
					int y = corr[1] + dirs[j][1];
					if (x >= 0 && y >= 0 && x < row && y < col && !visited[x][y] && grid[x][y] == 1) {
						visited[x][y] = true;
						dist[x][y] += level;
						num[x][y]++;
						queue.add(new int[]{x, y});
					}
				}
			}
		}		
	}
}