/**
* An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
* 
* For example, given the following image:
* 
* [
* "0010",
* "0110",
* "0100"
* ]
* and x = 0, y = 2,
* Return 6.
*/

/*
solution 1: DFS
用left，right，up，down四个变量分别表示最左边，最右边最上面和最下面，最后面积就是(right-left+1) * (down-up+1)
dfs查找的时候如果四周有没visited过的黑点就继续search，同时更新四个变量。
*/

public class SmallestRectangleEnclosingBlackPixels {
  public int minArea(char[][] image, int x, int y) {
    if (image == null || image.length == 0 || image[0].length == 0) return 0;
    int row = image.length;
	int col = image[0].length;
	int left = y;
    int right = y;
    int up = x;
    int down = x;
    int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] visited = new boolean[row][col];
	dfs(image, visited, x, y, left, right, up, down);
    return (right - left + 1) * (up - down + 1);    
  }
  private void dfs(char[][] image, boolean[][] visited, int x, int y, int left, int right, int up, int down) {
    int row = image.length;
	int col = image[0].length;
	if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || image[x][y] == '0') return;
	visited[x][y] = true;
	left = Math.min(left, y);
	right = Math.max(right, y);
	up = Math.min(up, x);
	down = Math.max(down, x);
	for (int[] dir : dirs) {
	  dfs(image, visited, x + dir[0], y + dir[1], left, right, up, down);
	}
  }
} 

/*
solution 2: binary search 
考虑枚举的方式，四个边界的范围分别是：left: [0, y+1], right: [y, col-1], up: [0, x+1], down: [x, row-1]
那么分别二分找四个边界。binary search的复杂度是mlogn + nlogm，优于dfs
*/

public class SmallestRectangleEnclosingBlackPixels {
  public int minArea(char[][] image, int x, int y) {
    if (image == null || image.length == 0 || image[0].length == 0) return 0;
    int left = 0;
	int right = 0;
	int up = 0;
	int down = 0;
    	
  }
  private int binarySearch (char[][] image, int lo, int hi, int left, int right, boolean col, boolean inc) {
    while (lo <= hi) {
	  int k = left;
	  int mid = lo + (hi - lo) / 2;
	  // k < right: find a black pixel 
	  // lo = mid + 1 if right or down, hi = mid if left or up 
	  while (k < right && (col ? image[k][mid] : image[mid][k])) {
	    k++;
	  }
	  if (k < r)
	} 
  }
} 













