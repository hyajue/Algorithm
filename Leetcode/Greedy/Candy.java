/**
* There are N children standing in a line. Each child is assigned a rating value.
* 
* You are giving candies to these children subjected to the following requirements:
* 
* Each child must have at least one candy.
* Children with a higher rating get more candies than their neighbors.
* What is the minimum candies you must give?

Example
Given ratings = [1, 2], return 3.

Given ratings = [1, 1, 1], return 3.

Given ratings = [1, 2, 2], return 4. ([1,2,1]).
*/

/*
复杂度
时间 O(n) 空间O(n)

思路：贪心 双向爬坡
先从左往右遍历，确保每个孩子根他左边的孩子相比，如果分高，则糖要多1个，如果分比左边低，就只给一颗
然后我们再从右往左遍历，确保每个孩子跟他右边的孩子相比，如果分高则糖至少多1个
双向爬坡后得到最少给糖数
*/

public class Candy {
  public int candy(int[] ratings) {
    if (ratings.length <= 1){
			return ratings.length;
		}  
		int[] candies = new int[ratings.length];
		//从左到右
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i-1]) {
				candies[i] = candies[i-1] + 1;
			}
		}
		for (int i = ratings.length - 2; i>= 0; i--) {
			if (ratings[i] > ratings[i+1]) {
				candies[i] = Math.max(candies[i], candies[i+1] + 1);
			}
		}
		int sum = ratings.length;
		for (int item : candies) {
			sum += item;
		}
		return sum;
  }
} 
