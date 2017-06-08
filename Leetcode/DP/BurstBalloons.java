/**
* Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
* 
* Find the maximum coins you can collect by bursting the balloons wisely.
* 
* Note: 
* (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
* (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
* 
* Example:
* 
* Given [3, 1, 5, 8]
* 
* Return 167
* 
*     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
*    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/

/*
复杂度
时间：O(n^3) 空间：O(n^2) 

思路：dp
这道题,难点是每打完一个气球,邻居会改变,所以不太容易找到一个枚举的方法
什么在这个过程中会不变呢? -> 总有一个最后被打掉的气球,假设气球k最后被打掉,则有:
1. [0,k-1]打气球是一个子问题,[k+1,n-1]打气球是另一个子问题,这两个子问题都需要得到最优解
2. 上面两个子问题通过气球k隔离,由于气球k最后才被打,所以“边界”气球不会发生改变
维护一个二维数组res，res[i][j]表示[i-1]和[j+1](所谓的边界)不变时,把[i,j]之间的气球都打掉的最高得分
最终所求结果是res[0][n-1]
如何状态转移？　－＞ 枚举最后一个打掉的气球k -> [i, j]
[i...k-1][k+1...j]得分都是最优的 + 打掉气球k的得分： nums[i-1]*nums[k]*nums[j+1]
for k -> [i, j]:
res[i][j] = max(res[i][k-1] + res[k+1][j] + nums[i-1]*nums[k]*nums[j+1])

对[i,j]的更新,选择从(j-i)从小到大的顺序 -> 实际上在枚举区间长度
最后在计算时注意各种边界情况,有的要置0有的要置1
*/

public class BurstBalloons {
  public int maxCoins(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int len = nums.length;
    int[][] res = new int [len][len];
    for (int curLen = 1; curLen <= len; curLen++) {
			for (int i = 0; i+curLen <= len; i++) {
				int j = i + curLen - 1;
				for (int k = i; k <= j; k++) {
					res[i][j] = Math.max(res[i][j], ((i <= k-1) ? res[i][k-1]:0) + ((k+1 <= j) ? res[k+1][j]:0) + ((i-1) >= 0 ? nums[i-1]:1)*nums[k]*((j+1 < len) ? nums[j+1]:1));
				}
			}
		}
    return res[0][len-1];		
  }
}