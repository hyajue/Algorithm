/**
* You are climbing a stair case. It takes n steps to reach to the top.
* 
* Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
* 
* Note: Given n will be a positive integer.
*/

/*
classic DP problem
solution 1: 
复杂度:
时间O(n) 空间O(n) 
*/
public class ClimbingStairs {
  public int climbStairs(int n) {
    if (n < 2) return n;
	int[] res = new int[n+1];
	res[0] = 1;
	res[1] = 1;
	for (int i = 2; i <= n; i++) {
	  res[i] = res[i-1] + res[i-2];
	}
	return res[n];
  }
} 

/*
solution 2: 
复杂度:
时间O(n) 空间O(1) 
由递推式可知每次循环中只需要往前看两个状态便可算出新的结果 故不需要数组来保存状态了
用两个变量即可
*/

public class ClimbingStairs {
  public int climbStairs(int n) {
    if (n < 2) return n;
	// 或者开一个大小为3的数组： int[] state = new int[] {0,1,2};
	int state0 = 1;
	int state1 = 1;
	int state2 = 0;
	
	for (int i = 2; i <= n; i++) {
	  state2 = state0 + state1;
	  state0 = state1;
	  state1 = state2;
	}
	return state1;
  }
} 