/**
* Given an integer n, return the number of trailing zeroes in n!.
* 
* Note: Your solution should be in logarithmic time complexity.
*/

/*
solution 1
复杂度
时间O(logn)  空间O(1)

思路：recursive
要求算法时间复杂度为logn，所以绝对不是傻傻地做一遍阶乘再去做
思考一下，什么时候末尾才会出现0，我们知道只有2*5，或者n*10的时候才会在末尾出现0，其实10也可以看做一种2*5，
那么其实就是看我们这个n中包含多少个2*5了，而因为有5就一定会有2，因为5比2大，相反有2不一定有5，所以只需要考虑n中有多少个5就可以了。
此外，对于25这个数，我们知道25*4=100，末尾会有两个0，而4也比5小，所以当出现25时，会加上两个0，也就是说答案还要加上有多少个25。
以此类推，还要考虑125、625等等，所以这是一个需要递归来实现的过程
*/

public class FactorialTrailingZeroes {
  public int trailingZeroes(int n) {
    return n == 0 ? 0 : n/5 + trailingZeroes(n/5);  
  }
}

/*
solution 2
复杂度
时间O(logn) 空间O(1)

思路：iterative
同solution1 找有多少个5 
*/

public class FactorialTrailingZeroes {
  public int trailingZeroes(int n) {
    int cnt = 0;
		while (n > 0) {
			cnt += n / 5;
			n /= 5;
		}
		return cnt;
  }
}

//solution 2另一种写法 注意i要声明成为long 否则最后一次while判断可能溢出
public class FactorialTrailingZeroes {
  public int trailingZeroes(int n) {
		long i = 5;
		int cnt = 0;
		while (i <= n) {
			cnt += n / i;
			i = i*5;
		}
		return cnt;
  }
}