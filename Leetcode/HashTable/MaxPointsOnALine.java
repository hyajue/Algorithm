/**
* Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

/*
复杂度
时间：O(n^2) 空间：O(n)

思路：
维护一个斜率对应point个数的HashMap
两次循环对points中第i个和第j个进行比较：
设置重复点数duplicate，相同斜率点数count。内部循环每次结束后更新count：和点i相同斜率的点的最大数目
注意：点i可能有很多相同斜率点的集合，故内层遍历完之后取最大的集合的大小
外部循环每次更新res为之前结果和本次循环所得duplicate+count的较大值。
1.以一个点为参照求其他点连线的斜率，不需要计算斜率。
2.两次循环体现重点一中的相对关系，可以让j从i开始，节省时间。
*/
 
public class MaxPointsOnALine {
  public int maxPoints(Point[] points) {
    int res = 0;
    if (points == null || points.length == 0) return res;
    for (int i = 0; i < points.length; i++) {
			int cnt = 1;
			int duplt = 0;
			Map<Double, Integer> map = new HashMap<Double, Integer>();
			Point cur = points[i];
			for (int j = i + 1; j < points.length; j++) {
				Point other = points[j];
				if (cur == other) continue;
				if (other.x == cur.x && other.y == cur.y){
				  duplt++;	
				} else {
				  Double slope = 0.0;
				  if (other.x == cur.x) {
				      slope = Double.MAX_VALUE;
				  } else if (other.y == cur.y) {
				      slope = 0.0;
				  } else {
				      slope = (other.y - cur.y)/(double)(other.x-cur.x);
				  }
					if (!map.containsKey(slope)) {
						map.put(slope, 2);
					} else { 
					  map.put(slope, map.get(slope)+1);
					  // System.out.println("other.x = " + other.x + " map.get(slope) = " + map.get(slope));
					}
					cnt = Math.max(cnt, map.get(slope));
				}				
			}
			res = Math.max(res, cnt+duplt);
			// System.out.println("cnt = " + cnt + ", duplt = " + duplt + ", res = " + res);
		}
    return res;    
  }
}