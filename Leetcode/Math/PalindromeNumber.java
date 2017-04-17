/**
* Determine whether an integer is a palindrome. Do this without extra space.
* Could negative integers be palindromes? (ie, -1)
* 
* If you are thinking of converting the integer to string, note the restriction of using extra space.
* 
* You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
*/

/*
求一个整数是不是回文树 负数不是，0是
利用求余，除以10，这样y = y×10+余树，比较y和输入值是否相等，判断是不是回文
*/

public class PalindromeNumber {
	public boolean isPalindrome(int x) {
	  int res = x;
		int cmp = 0;
		if (x < 0) {
		  return false;
	  } else if (x == 0) {
		  return true;
	  } else {
		  while (x != 0) {
			  cmp = cmp*10 + x%10;
			  x /= 10;
		  }
		  return cmp == res ? true : false;
	  }
	}
} 