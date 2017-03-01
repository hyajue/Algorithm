/**
* Given an integer, convert it to a roman numeral.
* 
* Input is guaranteed to be within the range from 1 to 3999.
*/

/*
贪心的做法，每次选择能表示的最大值，把对应的字符串连起来
*/


public class IntegerToRoman {
	public String intToRoman(int num) {
		int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		
		StringBuilder curStrBlder = new StringBuilder();
		// greedy match 
		for (int i = 0; num != 0; i++) {
			while (num >= nums[i]) {
				num -= nums[i];
				curStrBlder.append(symbol[i]);
			}
		}
		return curStrBlder.toString();
	}
} 