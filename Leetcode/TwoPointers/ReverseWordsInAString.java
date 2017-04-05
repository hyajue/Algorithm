/**
* Given an input string, reverse the string word by word.
* 
* For example,
* Given s = "the sky is blue",
* return "blue is sky the".
*
* Clarification:
* What constitutes a word?
* A sequence of non-space characters constitutes a word.
* Could the input string contain leading or trailing spaces?
* Yes. However, your reversed string should not contain leading or trailing spaces.
* How about multiple spaces between two words?
* Reduce them to a single space in the reversed string.
*/

/*
复杂度
时间O(n) 空间O(1)

思路:双指针
先去掉字符串前后的空格 然后维护两个指针 一个从后往前扫 一直指向上一次最后一个字符的位置 它的前一个位置是空格
另一个指针指向下一个要插入字符的位置 注意怎么判断要加入空格的条件
*/

public class ReverseWordsInAString {
  public String reverseWords(String s) {
    s = s.trim();
    int prevChar = 0;
    int insertPos = 0;
    StringBuilder sb = new StringBuilder();
    for(int i = s.length() - 1; i >= 0; i--){
      if(s.charAt(i) != ' ') {
        sb.insert(insertPos, s.charAt(i));
        prevChar = i;
				/*
				注意这里判断插入空格的条件 
				因为有多个空格时 只能插入一个 所以我们选择prevChar-i等于1 且指针
				并非指向头字符时 加入一个空格 其他情况的空格均跳过
				*/
      } else if (i != 0 && prevChar - i == 1) {
        sb.append(" ");
        insertPos = sb.length();
      }
    }
    return sb.toString();
  }
}
