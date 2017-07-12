/**
* Find the K closest points to the origin in a 2D plane, given an array containing N points.
*/
/*
public class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
*/
 
/*
复杂度
时间：O(nlgK) 空间：O(k)

思路： heap
一般看到找K个最xx的题，一般都是用最大/最小堆来解决
*/
 
public List<Point> findKClosest(Point[] p, int k) {
  PriorityQueue<Point> pq = new PriorityQueue<>(10, new Comparator<Point>() {
    @Override
    public int compare(Point a, Point b) {
        return (b.x * b.x + b.y * b.y) - (a.x * a.x + a.y * a.y);
    }
  });
   
  for (int i = 0; i < p.length; i++) {
    if (i < k) {
      pq.offer(p[i]);
		} else {
      Point temp = pq.peek();
      if ((p[i].x * p[i].x + p[i].y * p[i].y) - (temp.x * temp.x + temp.y * temp.y) < 0) {
        pq.poll();
        pq.offer(p[i]);
      }
    }
  }
   
  List<Point> list = new ArrayList<>();
  while (!pq.isEmpty()) {
    list.add(pq.poll());
  }  
  return list;
}