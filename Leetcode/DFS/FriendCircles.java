/**
* There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
* 
* Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
* 
* Example 1:
* Input: 
* [[1,1,0],
*  [1,1,0],
*  [0,0,1]]
* Output: 2
* Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
* The 2nd student himself is in a friend circle. So return 2.
* Example 2:
* Input: 
* [[1,1,0],
*  [1,1,1],
*  [0,1,1]]
* Output: 1
* Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
* so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
* Note:
* N is in range [1,200].
* M[i][i] = 1 for all students.
* If M[i][j] = 1, then M[j][i] = 1.
*/

/*
复杂度
时间 O(n^2) 空间O(n) 

思路：DFS
题目要求找到图中连通分量的个数，因此需要使用深度优先搜索算法（DFS）找出连通分量。声明一个boolean数组visited记录每个点是否被遍历，
题目中的图用二维数组M存储，所以visited的大小即为M的行数，对每个点进行DFS，查找一遍代表该点的行，每次找到一个与其相连的点（即矩阵该行中数值为1的列数），
则对该点所在行再进行DFS，若在同一连通分量内，则visited为TRUE
*/

public class FriendCircles {
	public int findCircleNum(int[][] M) {
    if (M == null || M.length == 0) return 0;
		int numOfCircles = 0;
		boolean[] visited = new boolean[M.length];
		for (int i = 0; i < M.length; i++) {
		  if (!visited[i]) {
			  numOfCircles++;
				visited[i] = true;
				expandCircle(M, visited, i);
			}
 		}
		return numOfCircles;
	}
	
	private void expandCircle(int[][] M, boolean[] visited, int stdNum) {
	  for (int col = 0; col < M[0].length; col++) {
		  if (!visited[col] && col != stdNum && M[stdNum][col] == 1) {
			  visited[col] = true;
				expandCircle(M, visited, col);
			}
		}	
	}
} 