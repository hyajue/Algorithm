/**
* Given two strings S and T, determine if they are both one edit distance apart.
*/

/*
复杂度
时间O(n) 空间O(1)

思路：
首先两个字符串如果长度相差超过1 直接返回false
当两个字符串首次出现不同的字符时，根据定义，剩下的字符要相同才能返回true
case1: lenS　＞　lenT
S: xyzzzzz
T： xzzzzz

case2: lenT > lenS
S: xz
T: xyz 

case3: lenT == lenS 
qwe
qte
*/
 
public class OneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
		if (s == null || t == null) return false;
		int lenS = s.length();
		int lenT = t.length();
		String subStrS = null;
		String subStrT = null;
		if (Math.abs(lenS - lenT) > 1) return false;
		int minLen = Math.min(lenS, lenT);
		for (int i = 0; i < minLen; i++) {
			if (s.charAt(i) != t.charAt(i)) {
				// case3
				if (lenS == lenT) {
					subStrS = s.substring(i+1);
					subStrT = t.substring(i+1);
				// case1 
				} else if (lenS > lenT) {
					subStrS = s.substring(i+1);
					subStrT = t.substring(i);
				// case2
				} else {
					subStrS = s.substring(i);
					subStrT = t.substring(i+1);
				}
				return subStrS.equals(subStrT);
			}
		}
		//两字符串相等
		return false;
	}
}