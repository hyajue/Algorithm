/**
* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
* 
* For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
*/

/*
复杂度
时间O(nlogn) 空间O(n)

思路：优先队列
两个meeting冲突 就必然要两个rooms 有冲突的条件: meetingA.end > meetingB.start  
还是要先排序 然后指针i指向之前有冲突的最小的end的那个meeting -> 需要快速求得之前的最小end -> 利用优先队列

heap
因为要知道之前有overlap的最小的end，所以可以用一个min heap。每次检查新的start是否比heap的top元素小，是的话就把保存原来的end，同时放进新的end；否则就放新的end同时poll出原来的，因为没有overlap且新的end较大。最后heap的大小就是需要的房间数。比如：
[1, 5], [2, 4], [3, 6], [5, 7]

heap: [5]。[2, 4]的start是2，比5小，所以放入4。
heap: [4, 5]。接着[3 ,6]的start是3，比4小，所以又放入6。
heap: [4, 5, 6]。[5, 7]的start是5，比4大，因此poll出4，放入7。
heap: [5, 6, 7]。最后heap的size为3。
4被pop出来是因为[2, 4]和[5, 7]公用一个房间，只要放7进去就可以了。
*/

public class MeetingRoomsII {
  public int minMeetingRooms(Interval[] intervals) {
  // base case
  if(intervals == null || intervals.length == 0) return 0;
  // sort
  Arrays.sort(intervals, new Comparator<Interval>() {
	  public compare(Interval i1, Interval i2) {
		  return (i1.start - i2.start) ? i1.end - i2.end : i1.start - i2.start;
		}	
	});
  // min heap to store the end
  PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  minHeap.offer(intervals[0].end);
  for(int i = 1; i < intervals.length; i++) {
      // no overlap
      if(minHeap.peek() <= intervals[i].start) minHeap.poll();
      minHeap.offer(intervals[i].end);
  }
  
  return minHeap.size();
  }
}