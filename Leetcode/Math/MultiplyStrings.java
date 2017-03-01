/**
* Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
* 
* Note:
* 
* The length of both num1 and num2 is < 110.
* Both num1 and num2 contains only digits 0-9.
* Both num1 and num2 does not contain any leading zero.
* You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

/*
 multiplying each digit of the numbers at the corresponding positions and get the sum values at each position.
 That is how we do multiplication manually.
 
 reverse string so that the small digits at front
 trim the leading zero in the end 
*/


public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
			return "0";
		}
		// String class doesn't have reverse method, so use StringBuilder 
		String str1 = new StringBuilder(num1).reverse().toString();
		String str2 = new StringBuilder(num2).reverse().toString();
		
		// actual result length won't go beyond [num1.length()+num2.length()]
		int[] res = new int[num1.length()+num2.length()];
		
		// multiply each digit and sum at corresponding index
		for (int i = 0; i < str1.length(); i++) {
			for (int j = 0; j < str2.length(); j++) {
				res[i+j] += (str1.charAt(i) - '0') * (str2.charAt(j) - '0');
			}
		}
		StringBuilder sb = new StringBuilder();
		// calculate each digit 
		for (int i = 0; i < res.length; i++) {
			int mod = res[i] % 10;
			int carry = res[i] / 10;
			if (i < res.length - 1) {
				res[i+1] += carry;
			}
			sb.insert(0, mod);
		}
		while (sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}
		return sb.toString();
    }
} 
