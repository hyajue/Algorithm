/**
* Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
* 
* If the last word does not exist, return 0.
* 
* Note: A word is defined as a character sequence consists of non-space characters only.
* 
* For example, 
* Given s = "Hello World",
* return 5.
*/


public class LengthOfLastWord {
  public int lengthOfLastWord(String s) {
    if (s == null || s.length() == 0) return 0;
    int idx = s.length() - 1;
    // skip the trailing spaces 
    while (idx >= 0) {
	  if (s.charAt(idx) != ' ') break;
	  idx--;
	}
    // the ending idx 
    int end = idx;
    // if already uder flow, then return 0
    if (idx < 0) return 0;
    // search beginning idx 
    while (idx >= 0) {
	  if (s.charAt(idx) == ' ') break;
	  idx--;
	}
    int begin = idx;
    return end - begin;	
  }
}
 