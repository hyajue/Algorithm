/**
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* Design an algorithm to find the maximum profit. You may complete at most k transactions.
* 
* Note:
* You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
solution 1:
复杂度：
时间O(nk) 空间O(nk)
思路：
先按照之前的方法对数组进行统计，计算出无限制条件下的最少交易次数tradeCount和最大获益profitCount。如果这个最少交易次数已经小于k了，那么直接返回最大获益即可。同时也因为在k < tradeCount的情况下，进行动态规划的效率很低，所以要先进行处理来避免。

在动态规划的部分，维护两个数组：local和global。其中local[i][j]表示总交易次数为i截止到第j天并且在最后一天要做交易的情况下的最大获益，global[i][j]表示总交易次数为i截止到第j天的最大获益。

之所以在global之外还要维护一个local数组，是因为在计算global[i][j]时，面临两种情况：

最后一天不做交易，那么直接等于global[i][j - 1]
最后一天要做交易，那么又需要分别考虑罪有一天是否有收益的问题，所以要增加一个local数组进行辅助
递推公式：

int diff = prices[j] - prices[j - 1];
local[i][j] = Math.max(global[i - 1][j - 1], local[i][j - 1] + diff);
global[i][j] = Math.max(global[i][j - 1], local[i][j]);

其中：local[i][j] = Math.max(global[i - 1][j - 1], local[i][j - 1] + diff)，
当diff < 0时，在最后一条做交易必然是亏的，所以其实此时local[i][j]直接等于global[i - 1][j - 1]；
当diff > 0时，本来应该比较两种情况的，global[i - 1][j - 1] + diff和local[i][j - 1] + diff，
但是通过以下推断我们可以知道local[i][j - 1] > global[i - 1][j - 1]，所以无须比较。

*/

public class BestTimeToBuyAndSellStockIV {
  public int maxProfit(int k, int[] prices) {
    int days = prices.length;
    int tradeCount = 0, profitCount = 0, rangeProfitCount = 0;
    for (int i = 1; i < days; i++) {
      if (prices[i - 1] < prices[i]) {
          rangeProfitCount += prices[i] - prices[i - 1];
          if (i == days - 1) {
              profitCount += rangeProfitCount;
              tradeCount += 1;
          }
      } else if (rangeProfitCount > 0) {
          profitCount += rangeProfitCount;
          tradeCount += 1;
          rangeProfitCount = 0;
      }
    }
    if (tradeCount <= k)
      return profitCount;
  
    int[][] global = new int[k + 1][days];
    int[][] local = new int[k + 1][days];
    for (int i = 1; i <= k; i++) {
      for (int j = 1; j < days; j++) {
          int diff = prices[j] - prices[j - 1];
          local[i][j] = Math.max(global[i - 1][j - 1], local[i][j - 1] + diff);
          global[i][j] = Math.max(global[i][j - 1], local[i][j]);
      }
    }
    return global[global.length - 1][global[0].length - 1];
  }
} 

/*
solution 2:
复杂度：
时间O(n) 空间O(k) 
最多可以进行k次交易的算法： 还是使用“局部最优和全局最优解法”
维护两种量，一个是当前到达第i天可以最多进行j次交易，最好的利润是多少（global[i][j]），
另一个是当前到达第i天，最多可进行j次交易，并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）
递推式： global[i][j] = max(local[i][j],global[i-1][j])，
就是取当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）。
对于局部变量的维护： local[i][j] = max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，
也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），
第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，并不会增加交易次数，
而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件
上面的算法中对于天数需要一次扫描，而每次要对交易次数进行递推式求解，所以时间复杂度是O(n*k)，如果是最多进行两次交易，那么复杂度还是O(n)。空间上只需要维护当天数据皆可以，所以是O(k)
*/

public class BestTimeToBuyAndSellStockIV {
  public int maxProfit(int k, int[] prices) {
    int days = prices.length;
    int tradeCount = 0, profitCount = 0, rangeProfitCount = 0;
    for (int i = 1; i < days; i++) {
      if (prices[i - 1] < prices[i]) {
          rangeProfitCount += prices[i] - prices[i - 1];
          if (i == days - 1) {
              profitCount += rangeProfitCount;
              tradeCount += 1;
          }
      } else if (rangeProfitCount > 0) {
          profitCount += rangeProfitCount;
          tradeCount += 1;
          rangeProfitCount = 0;
      }
    }
    if (tradeCount <= k)
      return profitCount;
  
    int[] global = new int[k + 1];
    int[] local = new int[k + 1];
    for (int i = 0; i < prices.length - 1; i++) {
      int diff = prices[i+1] - prices[i];
	  for (int j = k; j >= 1; j--) {
          local[j] = Math.max(global[j - 1]+(diff>0 ? diff:0), local[j] + diff);
          global[j] = Math.max(global[j], local[j]);
      }
    }
    return global[k];
  }
} 
