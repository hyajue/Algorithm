/**
* Given a column title as appear in an Excel sheet, return its corresponding column number.
* 
* For example:
* 
*     A -> 1
*     B -> 2
*     C -> 3
*     ...
*     Z -> 26
*     AA -> 27
*     AB -> 28 
*/

/*
复杂度
时间 O(N) 空间 O(1)

思路
得到数字，其实就是把26进制的数转换为10进制的数。算法就是基本的进制转换方法，从后往前第n位的值乘上26^(n-1)。这里26进制数是1开始的，即A是1。
*/

public class ExcelSheetColumnNumber {
  public int titleToNumber(String s) {
    int num = 0;
    int pow = 1;
    for (int i = s.length() - 1; i >= 0; i--) {
	  num += (s.charAt(i) - 'A' + 1) * pow;
	  pow *= 26;
	}
    return num;    
  }
}
 	
	