/**
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
* 
* You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
* After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
* Example:
* 
* prices = [1, 2, 3, 0, 2]
* maxProfit = 3
* transactions = [buy, sell, cooldown, buy, sell]
*/

/*
这道题又是关于买卖股票的问题，之前有四道类似的题目Best Time to Buy and Sell Stock 买卖股票的最佳时间，Best Time to Buy and Sell Stock II 买股票的最佳时间之二， Best Time to Buy and Sell Stock III 买股票的最佳时间之三和Best Time to Buy and Sell Stock IV 买卖股票的最佳时间之四。而这道题与上面这些不同之处在于加入了一个冷冻期Cooldown之说，就是如果某天卖了股票，那么第二天不能买股票，有一天的冷冻期。这道题我不太会，于是看到了网上大神的解法，点这里。根据他的解法，此题需要维护三个一维数组buy, sell，和rest。其中：

buy[i]表示在第i天之前最后一个操作是买，此时的最大收益。

sell[i]表示在第i天之前最后一个操作是卖，此时的最大收益。

rest[i]表示在第i天之前最后一个操作是冷冻期，此时的最大收益。

我们写出递推式为：

buy[i]  = max(rest[i-1] - price, buy[i-1]) 
sell[i] = max(buy[i-1] + price, sell[i-1])
rest[i] = max(sell[i-1], buy[i-1], rest[i-1])

上述递推式很好的表示了在买之前有冷冻期，买之前要卖掉之前的股票。一个小技巧是如何保证[buy, rest, buy]的情况不会出现，这是由于buy[i] <= rest[i]， 即rest[i] = max(sell[i-1], rest[i-1])，这保证了[buy, rest, buy]不会出现。

另外，由于冷冻期的存在，我们可以得出rest[i] = sell[i-1]，这样，我们可以将上面三个递推式精简到两个：

buy[i]  = max(sell[i-2] - price, buy[i-1]) 
sell[i] = max(buy[i-1] + price, sell[i-1])
 

我们还可以做进一步优化，由于i只依赖于i-1和i-2，所以我们可以在O(1)的空间复杂度完成算法，
*/

public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {  
        if (prices.length <= 1) {  
            return 0;  
        }  
        if (prices.length == 2) {  
            if (prices[1] > prices[0]) {  
                return prices[1] - prices[0];  
            }  
            return 0;  
        }  
          
        int p_buy = -prices[0], buy;  
        int p_sell = Integer.MIN_VALUE, sell = 0;  
        int p_rest = 0, rest = 0;  
          
        for (int i = 1; i < prices.length; i++) {  
            buy = Math.max(p_rest-prices[i], p_buy);   
            sell = Math.max(p_buy+prices[i], p_sell);  
            rest = Math.max(Math.max(p_sell, p_buy),  p_rest);  
              
            p_buy = buy;  
            p_sell = sell;  
            p_rest = rest;  
              
        }  
        return Math.max(sell, rest);  
    }  
}


 
