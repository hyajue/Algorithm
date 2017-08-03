/**
* There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
* 
* The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs0 is the cost of painting house 0 with color red; costs1 is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
* 
* Note: All costs are positive integers.
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：dp
Maintain a array of min cost called min_cost, each column represent a color and each row represent a houses. 

min_cost[i][0] represents that, when the ith house is painted Red, the min cost of 0 to ith houses. Thus we have the following:

min_cost{i} = min( 
//since the current color cannot be the same as the previous one 
cost[i][R] + min(cost{i-1, B}, cost{i-1, G}), 
cost[i][B] + min(cost{i-1, R}, cost{i-1, G}), 
cost[i][G] + min(cost{i-1, R}, cost{i-1, B}) 
)
*/
 
public class PaintHouse {
  public int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;
    int[][] res = new int[costs.length][3];
    //初始化res数组
		for (int i = 0; i < 3; i++) {
      res[0][i] = costs[0][i];
		}		
		
		for (int i = 1; i < costs.length; i++) {
		  res[i][0] = costs[i][0] + Math.min(res[i-1][1], res[i-1][2]);
      res[i][1] = costs[i][1] + Math.min(res[i-1][0], res[i-1][2]);
	    res[i][2] = costs[i][2] + Math.min(res[i-1][0], res[i-1][1]);			
		}
		
		return Math.min(res[res.length-1][0], Math.min(res[res.length-1][1], res[res.length-1][2]));
  }
}

/*
复杂度
时间：O(n) 空间：O(1)

思路：dp
利用输入数组记录dp结果 不需要额外存储空间
*/

public class PaintHouse {
  public int minCost(int[][] costs) {
    if(costs == null || costs.length == 0) return 0;
    for(int i = 1; i < costs.length; i++){
      costs[i][0] = costs[i][0] + Math.min(costs[i - 1][1], costs[i - 1][2]);
      costs[i][1] = costs[i][1] + Math.min(costs[i - 1][0], costs[i - 1][2]);
      costs[i][2] = costs[i][2] + Math.min(costs[i - 1][0], costs[i - 1][1]);
    }
    return Math.min(costs[costs.length - 1][0], Math.min(costs[costs.length - 1][1], costs[costs.length - 1][2]));
  }
}