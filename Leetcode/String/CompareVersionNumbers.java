/**
* Compare two version numbers version1 and version2.
* If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
* 
* You may assume that the version strings are non-empty and contain only digits and the . character.
* The . character does not represent a decimal point and is used to separate number sequences.
* For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
* 
* Here is an example of version numbers ordering:
* 
* 0.1 < 1.1 < 1.2 < 13.37
*/

/*
复杂度
时间：O(n)		空间:O(n)

思路：
先按照小数点把两个String给分开 然后按位比较 注意要每一位要转化成int再比 因为会有一些奇怪的cases例如000比0
转化成int后会自动都变为0
如果有一个数组先遍历完 要检查另一个数组后面的位数 如果不全是零 则后面的大 
*/

public class CompareVersionNumbers {
  public int compareVersion(String version1, String version2) {
    String[] ver1 = version1.split("\\.");
		String[] ver2 = version2.split("\\.");
		
		int idx = 0;
		while (idx < ver1.length && idx < ver2.length) {
			int num1 = Integer.valueOf(ver1[idx]);
			int num2 = Integer.valueOf(ver2[idx]);
			if (num1 > num2) {
				return 1;
			} else if (num2 > num1) {
				return -1;
			}
			idx++;
		}
		if (idx < ver1.length) {
			while (idx < ver1.length) {
				int num1 = Integer.valueOf(ver1[idx]);
				if (num1 != 0) return 1;
				idx++;
			}
			return 0;
		} else if (idx < ver2.length) {
			while (idx < ver2.length) {
				int num2 = Integer.valueOf(ver2[idx]);
				if (num2 != 0) return -1;
				idx++;
			}
			return 0;
		} else {
			return 0;
		}
  }
}
 