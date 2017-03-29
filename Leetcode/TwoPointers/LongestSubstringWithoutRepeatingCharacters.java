/**
* Given a string, find the length of the longest substring without repeating characters.
* 
* Examples:
* 
* Given "abcabcbb", the answer is "abc", which the length is 3.
* 
* Given "bbbbb", the answer is "b", with the length of 1.
* 
* Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

/*
复杂度
时间 O(n) 空间O(n)

思路：双指针(或叫移动窗口法)
维护一个窗口，每次关注窗口中的字符串，在每次判断中，左窗口和右窗口选择其一向前移动 
同样是维护一个HashSet, 正常情况下移动右窗口，如果没有出现重复则继续移动右窗口，如果发现重复字符，则说明当前窗口中的串已经不满足要求，
继续移动有窗口不可能得到更好的结果，此时移动左窗口，直到不再有重复字符为止，中间跳过的这些串中不会有更好的结果，因为他们不是重复就是更短
因为左窗口和右窗口都只向前，所以两个窗口都对每个元素访问不超过一遍，因此时间复杂度为O(2*n)=O(n),是线性算法。空间复杂度为HashSet的size,是O(n)
*/

public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) return 0;
		Set<Character> set = new HashSet<Character>();
		int max = 0;
		int slow = 0;
		int fast = 0;
		while (fast < s.length()) {
			if (set.contains(s.charAt(fast))) {
				if (max < fast - slow) { //注意把fast指向的字符除去 所以长度是(fast - slow) 
					max = fast - slow;
				}
				while (s.charAt(slow) != s.charAt(fast)) {
					set.remove(s.charAt(slow));
					slow++;
				}
				slow++;
			} else {
				set.add(s.charAt(fast));
			}
			fast++;
		}
		max = Math.max(max, fast - slow);
		return max;
  }
} 