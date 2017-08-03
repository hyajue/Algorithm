/*
* There is a fence with n posts, each post can be painted with one of the k colors.
* 
* You have to paint all the posts such that no more than two adjacent fence posts have the same color.
* 
* Return the total number of ways you can paint the fence.
* 
* Note:
* n and k are non-negative integers
*/ 

/*
复杂度
时间：O(n) 空间：O(n)

思路：dp
只允许两个连续的fence时同样的颜色，所以对于dp[i]而言，其状态不仅与dp[i-1]有关，也与dp[i-2]有关。

我们可以分两种情况考虑，一种是当前fence颜色与上个fence颜色不同，一种是当前fence颜色与上个fence颜色相同。对于前者，显然dp[i] = (k - 1) * dp[i - 1]。 对于后者，由于题目要求不能连续三个fence颜色相同，我们只要保证上上个fence与当前fence颜色不同即可，此时dp[i] = 1 * (k - 1) * dp[i - 2]。

所以 dp[i] = (k - 1) * dp[i - 1] + (k - 1) * dp[i - 2]
当然, 用两个变量代替int数组，时间复杂度是可以降至O(1)的。

另外，这道题也可以有Follow up比如题目改成最多允许3个fence有相同颜色，结果怎么样？
用上面类似的分析，可得

dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1) * 1 + dp[i - 3] * (k - 1) * 1 * 1
*/
public class PaintFence {
  public int numWays(int n, int k) {
  	if (n == 0) return 0;
		if (n == 1) return k;
		int[] res = new int[n];
		
		res[0] = k;
		res[1] = k * (k-1) + k;
		
		for (int i = 2; i < n; i++) {
		  res[i] = res[i-1] * (k-1) + res[i-2] * (k-1);	
		}
		return res[n-1];
  }
}

//同样思路，空间：O(1)的写法
public class PaintFence {
  public int numWays(int n, int k) {
  	if (n == 0) return 0;
		if (n == 1) return k;
		
		int res0 = k;
		int res1 = k * (k-1) + k;
		int res2 = 0;
		
		for (int i = 2; i < n; i++) {
		  res2 = res1 * (k-1) + res0 * (k-1);
      res0 = res1;
      res1 = res2;			
		}
		return res1;
  }
}
