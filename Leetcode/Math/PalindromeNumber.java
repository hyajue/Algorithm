/**
* Determine whether an integer is a palindrome. Do this without extra space.
*/

/*
求一个整数是不是回文树 负数不是，0是
利用求余，除以10，这样y = y×10+余树，比较y和输入值是否相等，判断是不是回文
*/

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
			return false;
		}
		else if (x == 0) {
			return true;
		}
		else {
			int res = x;
			int tmp = 0;
			while (x != 0) {
				tmp = tmp*10 + x%10;
				x /= 10;
			}
			return tmp == res ? true : false;
		}
    }
} 