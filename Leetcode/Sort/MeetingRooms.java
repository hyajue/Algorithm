/**
* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
* 
* For example, Given [[0, 30],[5, 10],[15, 20]], return false.
*/

/*
复杂度
时间O(nlogn) 空间O(1)

思路：直接排序
注意这里对intervals对象进行排序 需要自己定义好comparator，也就是要给定sort函数一个自定义的排序规范
需要重写compare方法
*/

public class MeetingRooms {
  public boolean canAttendMeetings(Interval[] intervals) {
	  if (intervals == null || intervals.length == 0) return true;
		//重写compare方法 按照开始时间排序 
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, interval i2) {
				return (i1.start == i2.start) ? i1.end - i2.end : i1.start - i2.start;
			}
		} );
		
		int late = intervals[0].end;
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < late) return false;
			late = Math.max(late, intervals[i].end);
		}
		return true;
  }
}