/**
* The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
* 
* P   A   H   N
* A P L S I I G
* Y   I   R
* And then read line by line: "PAHNAPLSIIGYIR"
* Write the code that will take a string and make this conversion given a number of rows:
* 
* string convert(string text, int nRows);
* convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

/*
复杂度
时间 O(n)  空间O(n)

思路
先将s转化为字符数组sList，便于操作。
再看nRows要求转化为多少行，就建立多大的StringBuilder数组sb[]
注意，建立完之后要用for循环对每一个StringBuilder[i]初始化：sb[i] = new StringBuilder();
然后用指针ptr循环字符数组sList：顺序从0到nRows-1每一行放入一个字符sList[ptr++]，然后逆序从nRows-2到1每一行放入一个字符sList[ptr++]，直到ptr == sList.length.
以上就将竖向ZigZag形式的字符串以横向读取顺序存入了数组sb中，将sb[ptr]中的所有字符串都合并到sb[0]中即可。
*/

public class ZigZagConversion {
	public String convert(String s, int numRows) {
	  char[] sList = s.toCharArray();
		int len = s.length();
		StringBuilder[] sb = new StringBuilder[numRows];
		for (int i = 0; i < numRows; i++) {
		  sb[i] = new StringBuilder();
		}
		int ptr = 0;
		while (ptr < len) {
		  for (int idx = 0; idx < numRows && ptr < len; idx++) {
			  sb[idx].append(sList[ptr]);
				ptr++;
			}
			for (int idx = numRows - 2; idx >= 1 && ptr < len; idx--) {
			  sb[idx].append(sList[ptr]);
				ptr++;
			}
		}
		for (int idx = 1; idx < sb.length; idx++) {
		  sb[0].append(sb[idx]);
		}
		return sb[0].toString();
	}
} 
