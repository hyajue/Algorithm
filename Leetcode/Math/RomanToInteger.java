/**
* Given a roman numeral, convert it to an integer.
* 
* Input is guaranteed to be within the range from 1 to 3999.
*/

/*
采用HashMap来存储各种罗马字符和数字对应关系

罗马数字有如下符号：

基本字符			I		V		X		L		C			D			M
对应阿拉伯数字	1		5		10	50	100		500		1000

计数规则：
相同的数字连写，所表示的数等于这些数字相加得到的数，例如：III = 3
小的数字在大的数字右边，所表示的数等于这些数字相加得到的数，例如：VIII = 8
小的数字，限于（I、X和C）在大的数字左边，所表示的数等于大数减去小数所得的数，例如：IV = 4
正常使用时，连续的数字重复不得超过三次
在一个数的上面画横线，表示这个数扩大1000倍（本题只考虑3999以内的数，所以用不到这条规则）
*/

public class RomanToInteger {
	public int romanToInt(String s) {
		if (s == null || s.length() == 0) {
		return 0;
		}     
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
	
		int len = s.length();
		int result = map.get(s.charAt(len-1));
		for (int i = len - 2; i >= 0; i--) {
			if (map.get(s.charAt(i+1)) <= map.get(s.charAt(i))) {
				result += map.get(s.charAt(i));
			} else {
				result -= map.get(s.charAt(i));
			}
		}
		return result;
	}
} 
