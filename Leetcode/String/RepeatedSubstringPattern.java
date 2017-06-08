/**
* Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
* 
* Example 1:
* Input: "abab"
* 
* Output: True
* 
* Explanation: It's the substring "ab" twice.
* Example 2:
* Input: "aba"
* 
* Output: False
* Example 3:
* Input: "abcabcabcabc"
* 
* Output: True
* 
* Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

/*
复杂度
时间：O(n^2) 空间：O(n)

思路：
挨个试,尝试的长度从[1,len/2]
*/

public class RepeatedSubstringPattern {
  public boolean repeatedSubstringPattern(String s) {
    int len = s.length();
    for (int i = len/2; i > 0; i--) {
			if (len % i == 0) {
				int parts = len / i;
				String sub = s.substring(0, i);
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < parts; j++) {
					sb.append(sub);
				}
				if (sb.toString().equals(s)) return true;
			}
		}
    return false;    
  }
}

/*
复杂度
时间：O(n) 空间：O(n)

思路：
首先如果字符串的字母数小于两个，那也不用判断了，一定不可能是多个子字符串重复出来的。

维护两个指针，一个快一个慢。快的从第二个字母开始往后遍历，找到与第一个字母一样的，就停下来开始判断，慢的从第一个字母开始，两个指针一起往后遍历，看是不是可以完全一致，
一直到最后一个字母，如果从后面开始的与从头开始的一模一样，说明是存在重复的字符串，并且由它重复组成的。

如果遍历时又出现不一样了，这时候说明还不是重复的，就要继续当做从头开始找了，继续往后遍历快的标记，找与第一个字母一样的，然后重复上面的过程，注意找到后慢的标记需要回到第一个字母。

不管找没找到，当快的遍历到最后字母了就停止遍历了，这时如果是没找到的状态，那就直接false了，如果是找到的状态，那么有可能是确实重复组成的，也有可能只是最后一小节和前面的一样，
中间的一段还是没有重复的，这时候根据慢的标记来判断，如果慢的指针已经走过了整个字符串的一半，就说明至少是二分之一的子字符串重复两次得到的，或者更小的字符串重复多次。
如果慢的标记还没走过一半，说明中间还有一部分并没有来得及重复，这时候就依然是false了。在判断慢的是否过半了时，由于存在整个字符串长度可能为基数也可能为偶数的情况，
所以用慢标记的位置乘以二来和整体长度作比较进行判断比较合适。
*/ 

public class RepeatedSubstringPattern {
  public boolean repeatedSubstringPattern(String s) {
	  if (s.length() < 2) return false;
    char[] sArr = s.toCharArray();
    int slow = 0;
    int fast = 1;
    boolean match = false;
    while (fast < sArr.length) {
			if (sArr[fast] == sArr[0]) {
				slow = 0;
				while (fast < sArr.length) {
					if (sArr[slow] == sArr[fast]) {
						match = true;
						slow++;
						fast++;
					} else {
						match = false;
						break;
					}
				}
			} else {
				match = false;
				fast++;
			}
		}
    if (match && low * 2 >= sArr.length) {
			return true;
		}		
		return false;
	}
}