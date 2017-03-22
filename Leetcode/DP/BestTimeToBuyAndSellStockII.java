/**
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
复杂度:
时间:O(n) 空间O(n)

思路：贪心法
允许多次买卖 -> 尽可能低买高卖
*/

public class BestTimeToBuyAndSellStockII {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) return 0;
    int profit = 0;
	for (int i = 1; i < prices.length; i++) {
	  if (prices[i] > prices[i-1]) {
	    profit += prices[i] - prices[i-1];
	  }
	}
    return profit;   	
  }
} 