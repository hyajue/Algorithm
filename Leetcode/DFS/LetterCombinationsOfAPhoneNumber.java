/**
* Given a digit string, return all possible letter combinations that the number could represent.
* 
* A mapping of digit to letters (just like on the telephone buttons) is given below.
* 
* Input:Digit string "23"
* Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
* Note:
* Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

/*
复杂度：
时间： NP O(3^n) 空间： O(n)

思路：回溯法
用HashMap存储数字对应的字母 
这种题型都要利用返回void的helper函数，然后判断在什么条件的时候加入result中，然后在循环里面加入，递归再删除。
当新生成的string的长度和digits的长度一样的时候就加入result里面 然后for循环可能的char, 就是每个数字对应的char[]里的元素。
*/

public class LetterCombinationsOfAPhoneNumber {
  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<String>();
    if (digits == null || digits.length() == 0) return res;
    //建立 数字-字母 对应表格
	Map<Character, char[]> map = new HashMap<Character, char[]>();
    map.put('0', new char[]{});
    map.put('1', new char[]{});
    map.put('2', new char[] { 'a', 'b', 'c'});
    map.put('3', new char[] { 'd', 'e', 'f'});
    map.put('4', new char[] { 'g', 'h', 'i'});
    map.put('5', new char[] { 'j', 'k', 'l'});
    map.put('6', new char[] { 'm', 'n', 'o'});
    map.put('7', new char[] { 'p', 'q', 'r', 's'});
    map.put('8', new char[] { 't', 'u', 'v'});
    map.put('9', new char[] { 'w', 'x', 'y', 'z'});
	
	StringBuilder sb = new StringBuilder();
	helper(res, map, sb, digits, 0);
	return res;
  }
  private void helper(List<String> res, Map<Character, char[]> map, StringBuilder sb, String digits, int idx) {
    if (digits.length() == sb.length()) {
	  res.add(sb.toString());
	  return;
	}
    for (char c : map.get(digits.charAt(idx))) {
      sb.append(c);
	  helper(res, map, sb, digits, idx+1);
	  sb.deleteCharAt(sb.length()-1);
	}	
  }
} 














