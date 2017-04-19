/**
* Given a positive integer, return its corresponding column title as appear in an Excel sheet.
* 
* For example:
* 
*     1 -> A
*     2 -> B
*     3 -> C
*     ...
*     26 -> Z
*     27 -> AA
*     28 -> AB 
*/

/*
复杂度
时间 O(logN) 空间 O(1)

思路
把10进制的转换成26进制，做法是除26取余，一直除到0，最后把余数逆序一下就行了。不过因为A是1，而不是0，相当于26进制的数都整体减1，才能对应上从0开始的十进制数。
*/

public class ExcelSheetColumnTitle {
  public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while (n != 0) {
	  sb.append((char)('A' + (n-1)%26));
	  n = (n-1) / 26;
	}
    return sb.reverse().toString(); 	
  }
} 	