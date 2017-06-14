/**
* A group of two or more people wants to meet and minimize the total travel distance. 
* You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
* The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
* For example, given three people living at (0,0), (0,4), and (2,2):
* 1 - 0 - 0 - 0 - 1
* |   |   |   |   |
* 0 - 0 - 0 - 0 - 0
* |   |   |   |   |
* 0 - 0 - 1 - 0 - 0
* The point (0,2) is an ideal meeting point, as the total travel
* distance of 2+2+2=6 is minimal. So return 6.
*/

/*
复杂度
时间：O(nm) 空间：O(nm)

思路：横纵轴分开
为了保证总长度最小,要尽量使路径不要有重复.
比如： 1 -> 2 -> 3 <- 4
如果起点是1,2,和4,那2->3和1->2->3两条路就有重复
为了尽量保证右边的点向左走，左边的点向右走，那就应该去这些点中间的点作为交点
由于是曼哈顿距离，可以分开计算横坐标和纵坐标，结果一样
所以算出各个横坐标到中点横坐标的距离，加上各个纵坐标到中点纵坐标的距离，就是结果
*/

public class BestMeetingPoint {
  public int minTotalDistance(int[][] grid) {
	  List<Integer> xCor = new ArrayList<Integer>();
		List<Integer> yCor = new ArrayList<Integer>();
		
		// get all coordinates
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					xCor.add(i);
					yCor.add(j);
				}
			}
		}
		int minDis = 0;
		// calculate distance from each xCor to middle points
    // no sorting needed because previously we get xCor based on ascending order of i
    for (int i = 0; i < xCor.size(); i++) {
			minDis += Math.abs(xCor.get(i) - xCor.get(xCor.size()/2);
		} 		
		
		Collections.sort(yCor);
		for (int j = 0; j < yCor.size(); j++) {
			minDis += Math.abs(yCor.get(j) - yCor.get(yCor.size()/2));
		}
	}
}