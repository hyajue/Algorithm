/**
* Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
* 
* For example, given the following triangle
* [
*      [2],
*     [3,4],
*    [6,5,7],
*   [4,1,8,3]
* ]
* The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
* 
* Note:
* Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

/*
solution 1:
复杂度
时间(n^2) 空间(n^2)
res[i][j]: minPath from nums[i][j] to bottom 
1. if i == bottom: res[i][j] = nums[bottom][j] -> 边界条件
2. if i != bottom: res[i][j]  Math.min(res[i-1][j], res[i-1][j-1]) + nums[i][j] 
*/

public class Triangle {
  public int minimumTotal(List<List<Integer>> triangle) {
    if (triangle == null || triangle.size() == 0) {
	  return 0;
	}  
	int row = triangle.size();
	int col = row;
	int[][] res = new int[row][col];
	for (int j = 0; j < col; j++) {
	  res[row-1][j] = triangle.get(row-1).get(j);
	}
	for (int i = row - 2; i >= 0; i--) {
	  for (int j = 0; j <= i; j++) {
	    res[i][j] = Math.min(res[i+1][j], res[i+1][j+1]) + triangle.get(i).get(j);
	  }  
	}
	return res[0][0];
  }	
} 

/*
solution 2:
复杂度
时间(n^2) 空间(n)
当前状态只取决于下一行当前列或下一行下一列 所以维护一位数组即可
*/

public class Triangle {
  public int minimumTotal(List<List<Integer>> triangle) {
    if (triangle == null || triangle.size() == 0) {
	  return 0;
	}  
	int row = triangle.size();
	int col = row;
	int[] res = new int[col];
	for (int j = 0; j < col; j++) {
	  res[j] = triangle.get(row-1).get(j);
	}
	for (int i = row - 2; i >= 0; i--) {
	  for (int j = 0; j <= i; j++) {
	    res[j] = Math.min(res[j], res[j+1]) + triangle.get(i).get(j);
	  }  
	}
	return res[0];
  }	
} 

/*
solution 3:
复杂度
时间(n^2) 空间(1)
基于solution 2. 如果输入数据允许修改 则可以利用输入矩阵的最后一行做in place的更新
*/







