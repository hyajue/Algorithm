/**
* Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
* 
* click to show follow up.
* 
* Follow up:
* Did you use extra space?
* A straight forward solution using O(mn) space is probably a bad idea.
* A simple improvement uses O(m + n) space, but still not the best solution.
* Could you devise a constant space solution?
*/

/*
复杂度
时间：O(n^2) 空间O(1)

思路：
要达到in place的效果,就要利用输入矩阵进行记录"零"的信息
如果某个位置出现了零,就把这个元素所在行和列的第一个元素置为零,相当于一个indicator
但是这样做就把第一行和第一列的元素破坏了,无法知道到底第一行和第一列的零是重置的还是自带的
所以还要维护两个标致量针对第一行和第一列特殊处理
*/

public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
	
	}
	
	}
 