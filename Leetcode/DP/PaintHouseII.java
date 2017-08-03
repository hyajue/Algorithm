/*
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * 
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs0 is the cost of painting house 0 with color 0; costs1 is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 * 
 * Note: All costs are positive integers.
 * 
 * Follow up: Could you solve it in O(nk) runtime?
*/

/*
复杂度
时间：O(nk) 空间:O(1)

思路:dp
与paint house I 一样,这道题每个房子对应K个颜色,所以不能用Math.min()方法求解了.
这里对房子i,每次记录上次最小和次小的开销,如果当前颜色跟最小是一个颜色,就加上次小开销,否则就
加上最小开销.
*/

public class PaintHouseII {
  public int minCostII(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;
    int min = 0;
    int minSec = 0;
    int minIdx = -1;
     
    for (int i = 0; i < costs.length; i++) {
      int curMin = Integer.MAX_VALUE;
      int curMinSec = Integer.MAX_VALUE;
      int curIdx = -1;
      for (int j = 0; j < costs[0].length; j++) {
        costs[i][j] = costs[i][j] + (minIdx == j ? minSec : min);
				if (costs[i][j] < curMin) {
				  curMinSec = curMin;
					curMin = costs[i][j];
					curIdx = j;
				} else if (costs[i][j] < curMinSec) {
				  curMinSec = costs[i][j];	
				}
			}		
      min = curMin;
			minSec = curMinSec;
			minIdx = curIdx;
		}
    return min;		
  }	
}