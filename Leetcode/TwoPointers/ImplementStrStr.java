/**
* Implement strStr().
* 
* Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

/*
solution 1:
复杂度
时间：O((n-m+1)*m)=O(n*m) 空间:O(1)

思路：暴力比对
假设原串的长度是n，匹配串的长度是m。对原串的每一个长度为m的字串都判断是否跟匹配串一致。
总共有n-m+1个子串，所以算法时间复杂度为O((n-m+1)*m)=O(n*m)，空间复杂度是O(1)
*/

public class ImplementStrStr {
  public int strStr(String haystack, String needle) {
    if (haystack == null || needle == null || needle.length() == 0) {
			return 0;
		}
		if (needle.length() > haystack.length()) {
			return -1;
		}
		for (int i = 0; i <= haystack.length() - needle.length(); i++) {
			boolean matchFlag = true;
			for (int j = 0; j < needle.length(); j++) {
				if (haystack.charAt(i+j) != needle.charAt(j)) {
					matchFlag = false;
					break;
				}
			}
			if (matchFlag) {
				return i;	
			}
		}
		return -1;
  }
} 

public int strStr(String haystack, String needle) {
  if (haystack == null || needle == null || needle.length() == 0) {
  	return 0;
  }
  
	if (needle.length() > haystack.length()) {
  	return -1;
  }

  int[] next = new int[needle.length()+1];  
  getNext(needle, next);
  int idx = 0;
	for (int i = 0; i < haystack.length(); i++) {
		while (idx < needle.length()) {
			if (idx == 0 && needle.charAt(idx) != haystack.charAt(i)) {
				break;
			} else if (needle.charAt(idx) == haystack.charAt(i)) {
				idx++;
				break;
			} else {
				idx = next[idx-1];
			}
		}
		if (idx == needle.length()) return true;
	}
  
	 
}

private void getNext(String needle, int[] next) {
	int i = 1;
	int j = 0;
	int len = needle.length();
	while (i < len) {
		if (needle.charAt(i) == needle.charAt(j)) {
			next[++i] = ++j;
		} else if (j == 0) {
			i++;
		} else {
			j = next[j];
		}
	}
}







