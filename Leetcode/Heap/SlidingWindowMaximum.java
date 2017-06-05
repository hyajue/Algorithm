/**
* Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
* 
* For example,
* Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
* 
* Window position                Max
* ---------------               -----
* [1  3  -1] -3  5  3  6  7       3
*  1 [3  -1  -3] 5  3  6  7       3
*  1  3 [-1  -3  5] 3  6  7       5
*  1  3  -1 [-3  5  3] 6  7       5
*  1  3  -1  -3 [5  3  6] 7       6
*  1  3  -1  -3  5 [3  6  7]      7
* Therefore, return the max sliding window as [3,3,5,5,6,7].
* 
* Note: 
* You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
* 
* Follow up:
* Could you solve it in linear time?
*/

/*
复杂度
时间:O(nlogK) 空间:O(k)

思路：heap
维护一个大小为k的最大堆,依次将原数组数据入堆,每次堆顶的元素即为窗口中的最大值.
注意当k == nums.length的情况：当pq.sise() == k时就要更新结果数组
*/

public class SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0) return new int[]{};
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, (a, b) -> (b - a));    
		int[] res = new int[nums.length-k+1];
		for (int i = 0; i < nums.length; i++) {
			pq.offer(nums[i]);
			if (pq.size() == k) {
				res[i+1-k] = pq.peek();
				pq.remove(nums[i+1-k]);
			}
		}
		return res;
  }
} 

/*
复杂度
时间:O(n) 空间:O(k)

思路：Deque
维护一个双向队列,队列里面存原数组元素的下标,下标对应的元素在队列从头到尾递减顺序排列,
这样每次队头的元素即为窗口中的最大值.每次加入新下标时,先判断是否能入队的条件,如果不能,就先从队尾出队到
条件满足为止.
*/

public class SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0) return new int[]{};
    Deque<Integer> deque = new LinkedList<Integer>();
    int[] res = new int[nums.length-k+1];
    for (int i = 0; i < nums.length; i++) {
			//每次将属于当前窗口的元素都移除
			if (!deque.isEmpty() && deque.peek() <= i - k) {
				deque.poll();
			}
			//队尾的元素必须比目前元素大,否则就一直出队到满足条件
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				deque.removeLast();
			}
			deque.offerLast(i);
			if (i+1 >= k) {
				res[i+1-k] = nums[deque.peek()];
			}
		}
    return res;		
  }
}