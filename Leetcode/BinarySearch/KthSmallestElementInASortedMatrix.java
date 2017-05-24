/**
* Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
* 
* Note that it is the kth smallest element in the sorted order, not the kth distinct element.
* 
* Example:
* 
* matrix = [
*    [ 1,  5,  9],
*    [10, 11, 13],
*    [12, 13, 15]
* ],
* k = 8,
* 
* return 13.
* Note: 
* You may assume k is always valid, 1 ≤ k ≤ n2.
*/

/*
solution 1
复杂度
时间：O((n*m)*logk) 空间：O(k)

思路：最大堆
用maxheap把全部元素放进heap里面，同时如果heap的size大于k就弹出，保持heap的size为k，
最后root的元素就是第k小
*/

public class KthSmallestElementInASortedMatrix {
  public int kthSmallest(int[][] matrix, int k) {
    // heap
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k + 1, (a, b) -> b - a);
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[0].length; j++) {
        maxHeap.offer(matrix[i][j]);
        if(maxHeap.size() > k) maxHeap.poll();
      }
    }
    return maxHeap.poll();
  }
}

/*
solution 2
复杂度
时间：O((n*m)*logk) 空间：O(k)

思路：二分

*/

public class KthSmallestElementInASortedMatrix {
  public int kthSmallest(int[][] matrix, int k) {
    int row = matrix.length;
    int left = matrix[0][0];
    int right = matrix[row-1][row-1];
    int[] idx = new int[2];
		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int num = cnt(matrix, mid, idx);
			if (num == k) {
				return matrix[idx[0]][idx[1]];
			} else if (num < k) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
			idx[0] = 0;
			idx[1] = 0;
		}
    return 0;		
  }
	
	private int cnt(int[][] matrix, int target, int[] idx) {
		int row = matrix.length;
		int res = 0;
		int i = row - 1;
		int j = 0;
		boolean foundIdx = false;
		while (i >= 0 && j < row) {
			if (matrix[i][j] <= target) {
				res += i + 1;
				j++;
			} else {
				i--;
			}
      if (j > res[1]) {
				idx[0] = i;
				idx[1] = j - 1;
			}			
		}
		return res;
	}
}
 

