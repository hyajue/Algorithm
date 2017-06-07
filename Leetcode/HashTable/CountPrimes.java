/**
* Count the number of prime numbers less than a non-negative number, n.
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：
维护一个boolean数组, boolean[i] indicates if number i is a prime number.
根据质数的特征,除了自己和1没有别的因数，可以通过枚举得到所有的质数：、
i->[2,根号n）, j->[i,n/i),如果j*i在[1,n)内,则把boolean[j*i]置为true
最后遍历一遍boolean,统计多少个位置被置true
*/

public class CountPrimes {
  public int countPrimes(int n) {
    if (n < 0) return 0;
    boolean[] res = new boolean[n];
    for (int i = 2; i*i < n; i++) {
			if (!res[i]) {
				for (int j = i; j*i < n; j++) {
					res[j*i] = true;
				}
			}
		}
    int cnt = 0;
    for (int i = 2; i < n; i++) {
			if (!res[i]) {
				cnt++;
			}
		}
    return cnt;    
  }
} 