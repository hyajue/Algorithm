/**
* Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
* 
* 
* Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
* 
* 
* The largest rectangle is shown in the shaded area, which has area = 10 unit.
* 
* For example,
* Given heights = [2,1,5,6,2,3],
* return 10.
*/



public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0 || heights == null) {
			return 0;
		}
		int maxArea = 0;
		int curArea = 0;
		LinkedList<Integer> stack = new LinkedList<Integer>();
		for (int i = 0; i < heights.length; i++) {
			while (!stack.isEmpty() && (heights[i] <= heights[stack.peek()])) {
				int idx = stack.pop();
				curArea = stack.isEmpty() ? i*heights[idx] : (i-stack.peek()-1)*heights[idx]; 
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int idx = stack.pop();
			curArea = stack.isEmpty() ? heights.length*heights[idx] : (heights.length-stack.peek()-1)*heights[idx];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
    }
} 

