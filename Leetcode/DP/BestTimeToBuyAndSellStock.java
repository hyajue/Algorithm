/**
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
* 
* Example 1:
* Input: [7, 1, 5, 3, 6, 4]
* Output: 5
* 
* max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
* Example 2:
* Input: [7, 6, 4, 3, 1]
* Output: 0
* 
* In this case, no transaction is done, i.e. max profit = 0.
*/

/*
solution 1: 
复杂度：
时间O(n), 空间O(n)
profit[i]: 第i天为止的最大收益
*/
 
public class BestTimeToBuyAndSellStock {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
  	  return 0;
  	}
   	int[] profit = new int[prices.length];
	int minPrice = prices[0];
	for (int i = 1; i < prices.length; i++) {
	  if (prices[i] < minPrice) {
	    minPrice = prices[i];
	  }
	  profit[i] = Math.max(profit[i-1], prices[i]-minPrice);
	}
	return profit[prices.length-1];
  }
}

/*
solution 2: 
复杂度：
时间O(n), 空间O(1)
整体-局部最优法
*/

public class BestTimeToBuyAndSellStock {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
  	  return 0;
  	}
   	int localMax = 0;
	int globalMax = 0;
	int minPrice = prices[0];
	for (int i = 1; i < prices.length; i++) {
	  if (prices[i] < minPrice) {
	    minPrice = prices[i];
	  } else {
	    localMax = prices[i] - minPrice;
	    globalMax = Math.max(globalMax, localMax);
	  }
    }
    return globalMax;
  }
}
