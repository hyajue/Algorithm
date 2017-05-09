/**
* Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
* 
* For example, given the following matrix:
* 
* 1 0 1 0 0
* 1 0 1 1 1
* 1 1 1 1 1
* 1 0 0 1 0
* Return 6.
*/

/*
复杂度
时间：O(n^2) 空间:(n)

思路
extension of problem: Largest Rectangle in Histogram
for each row i of matrix: 
	cut row i off and treat matrix as a histogram from row i to row N-1
	maintain an array height that keep track of largest rectangle of last row i-1
*/
public class MaximalRectangle {
  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length ==0) return 0;
    int maxArea = 0; // global max area
    int[] height = new int[matrix[0].length]; // store last row max area 
    for (int i = 0; i < matrix.length; i++) {
  	  for (int j = 0; j < matrix[0].length; j++) {
  		  height[j] = (matrix[i][j] == '0') ? 0 : (height[j] + 1);	  
  	  }
			maxArea = Math.max(largestRectangleArea(height), maxArea);
    }
    return maxArea;
  }
  
	private int largestRectangleArea(int[] height) {
	  if (height.length == 0) return 0;
		int maxArea = 0; 
		int curArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
				int idx = stack.pop();
				int h = height[idx];
				int w = stack.isEmpty() ? i : (i-1-stack.peek()); 
				curArea = h*w;
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
		  int idx = stack.pop();
		  int h = height[idx];
		  int w = stack.isEmpty() ? height.length : (height.length-1-stack.peek()); 
		  curArea = h*w;
		  maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}
}
