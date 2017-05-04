/**
* Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
* 
* The input string does not contain leading or trailing spaces and the words are always separated by a single space.
* 
* For example, Given s = "the sky is blue", return "blue is sky the". Could you do it in-place without allocating extra space?
*/

/*
复杂度
时间:O(n) 空间:O(1)

思路：双指针
first, reverse entire array;
second, reverse each of word
*/

public class ReverseWordsInAStringII {
  public void reverseWords(char[] s) {
    if (s == null || s.length < 2) return;
    reverse(s, 0, s.length-1);
    int idx = 0;
    for (int i = 0; i < s.length; i++) {
		  if (s[i] == ' ') {
				reverse(s, idx, i-1);
				idx = i+1;
			}
		}
		// last word doesn't get reversed in for loop
    reverse(s, idx, s.length-1);
    return;		
  }
	
	private void reverse(char[] s, int left, int right) {
		while (left < right) {
			char tmp = s[left];
			s[left] = s[right];
			s[right] = tmp;
			left++;
			right--;
		}
	}
}