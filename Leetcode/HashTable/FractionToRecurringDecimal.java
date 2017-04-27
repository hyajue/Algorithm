/**
* Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
* 
* If the fractional part is repeating, enclose the repeating part in parentheses.
* 
* For example,
* 
* Given numerator = 1, denominator = 2, return "0.5".
* Given numerator = 2, denominator = 1, return "2".
* Given numerator = 2, denominator = 3, return "0.(6)".
*/

/*
复杂度
时间O(n) 空间O(n)

思路：HashMap

难点：如何识别循环体？

解决方法：用一个HashMap记录每一个余数，当出现重复的余数时，那么将会进入循环，两个重复余数之间的部分就是循环体。

示例：1/13=0.076923076923076923...，当小数部分第二次出现0时，就意味着开始了循环，那么需要把076923用括号括起来，结果为0.(076923)。

涉及技巧：1）在不断相除的过程中，把余数乘以10再进行下一次相除，保证一直是整数相除；2）HashMap的key和value分别是<当前余数, 对应结果下标>，这样获取076923时就可根据value值来找。

注意点1：考虑正负数，先判断符号，然后都转化为正数；

注意点2：考虑溢出，如果输入为Integer.MIN_VALUE，取绝对值后会溢出。
*/

public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) { 
	  if (numerator == 0) return "0";
		if (denominator == 0) return String.valueOf(Integer.MAX_VALUE);
		
		StringBuilder sb = new StringBuilder();
		
		if ((numerator > 0) ^ (denominator > 0)) {
			sb.append("-");
		}
		
		long numL = (long)numerator;
    long denL = (long)denominator;
    numL = Math.abs(numL);
		denL = Math.abs(denL);
		
		//整数部分
		long res = numL/denL;
		sb.append(res);
   	//return if no remainder
    long rem = (numL%denL)*10;
    if (rem == 0) return sb.toString();
    
    //小数部分 存入hashMap
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		sb.append(".");
		while (rem != 0) {
			//如果前面出现过该余数 那么将会出现循环
			if (map.containsKey(rem)) {
				int start = map.get(rem);
				String part1 = sb.substring(0, start);
				String part2 = sb.substring(start);
				String ans = part1 + "(" + part2 + ")";
				return ans;
			}
			//没有出现过改余数 就继续往下面除
			map.put(rem, sb.length());
			res = rem / denL;
			sb.append(String.valueOf(res));
			rem = (rem%denL) * 10;
		}
		return sb.toString();
	}
}