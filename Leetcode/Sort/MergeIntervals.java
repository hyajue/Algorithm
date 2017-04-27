/**
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

/*
复杂度
时间：

思路：

*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) return res;
        Comparator<Interval> cmp = new Comparator<Interval>() {
            @Override
            Public int compare(Interval i1, Interval i2) {
                if (i1.start == i2.start) {
                  return i1.end - i2.end;    
                } 
                return i1.end - i2.end;
            }
        };
        Collections.sort(intervals, cmp);
        res.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            int 
        }
    }
}
