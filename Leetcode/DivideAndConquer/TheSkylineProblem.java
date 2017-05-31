/**
* A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
* 
*  Buildings Skyline Contour
* The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
* 
* For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
* 
* The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
* 
* For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
* 
* Notes:
* 
* The number of buildings in any input list is guaranteed to be in the range [0, 10000].
* The input list is already sorted in ascending order by the left x position Li.
* The output list must be sorted by the x position.
* There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
*/

/*
复杂度
时间：O(n^2) 空间：O(n)

思路：扫描线+优先队列
区分左右顶点的办法： 左右顶点的x照常记录.高度，左边用负值，右边用正值

这样把一个矩阵变换成一些左右顶点来进行判断。算法就是：首先按照x坐标进行排序之后，（点是二维坐标表示的，排序的时候需要自己写个comparator，x排序，如果x相同则按照height来排序）

然后扫描这些点，用priorityqueue来维持height的高度，核心：堆顶是所有顶点中最高的点，只要这个点没被移出堆，说明这个最高的矩形还没结束.
对于左顶点，将其加入堆中.对于右顶点，我们找出堆中其相应的左顶点，然后移出这个左顶点，同时也意味这这个矩形的结束

用最大堆，来解决矩形overlap的情况，就是矩形覆盖的时候，如何生成拐点，那么就是heap保持了最高的点，只要还没有遇见最高点的右边节点，
那么这个最高点还没有被移除，那么即使遇见了次高点，那么pre == cur，所以就忽略了这个点，然后遇见了最高点的右边顶点的时候，就可以pop最高点，
同时可以生成拐点，（加入当前最右边点的x值，和pop之后的，cur值，也就是当前的height）这样就生成了覆盖的拐点
*/

public class TheSkylineProblem {
  public List<int[]> getSkyline(int[][] buildings) {
    List<int[]> res = new ArrayList<int[]>();
    // max heap: sort height as decending order, larger ones at top 
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10000, new Comparator<Integer>(){
		  @Override
			public int compare(Integer height1, Integer height2) {
				return height2 - height1;
			}
		});    
    List<int[]> pos = new ArrayList<int[]>();
		/* 
		  build position list
      buildings[i][0]:Li, buildings[i][1]:Ri, buildings[i][2]:Hi
    */			 
		for (int[] point : buildings) {
		  // left edge as negtive, right edge as positive
			pos.add(new int[]{point[0], -point[2]});
      pos.add(new int[]{point[1], point[2]});			
		}
    // sort positions as ascending order of x-coordinate
    // if same x-coordinate, sort as ascending order of y-coordinate 
    Collections.sort(pos, new Comparator<int[]>(){
		  @Override
      public int compare(int[] point1, int[] point2) {
				if (point1[0] != point2[0]) {
					return point1[0] - point2[0];
				} else {
					return point1[1] - point2[1];
				}
			}			
		});
    //add horizontal line into pq
		pq.add(0);
		int prev = 0;
		for (int[] point : pos) {
			//put left edge height on heap
			if (point[1] < 0) {
				pq.offer(-point[1]);
			} else {
				// remove cooresponding left edge height if met right edge
        pq.remove(point[1]);				
			}
			int cur = pq.peek();
			/*
			  如果当前height跟前一个不同,证明出现了拐点,需要将拐点加入结果集
				堆顶元素是当前最大的height,所以就算有很多矩形overlap在一起,
				拐点只取决于最高的height.每次有新的left edge加入或者right edge移除,
				都有可能更新height
			*/
			if (cur != prev) {
				res.add(new int[]{point[0], cur});
				prev = cur;
			}
		}
		return res;
	}
}