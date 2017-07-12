/**
* Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
* 
* Example:
* For num = 5 you should return [0,1,1,2,1,2].
* 
* Follow up:
* 
* It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
* Space complexity should be O(n).
* Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：找规律,dp
1) We do not need check the input parameter, because the question has already mentioned that the number is non negative.

2) How we do this? The first idea come up with is find the pattern or rules for the result. Therefore, we can get following pattern

Index : 0 1 2 3   4 5 6 7     8 9 10 11  12 13 14 15

  num : 0 1 1 2   1 2 2 3     1 2 2  3   2  3  3  4

Do you find the pattern?

Obviously, this is overlap sub problem, and we can come up the DP solution. For now, we need find the function to implement DP.

dp[0] = 0;

dp[1] = dp[0] + 1;

dp[2] = dp[0] + 1;

dp[3] = dp[1] + 1;

dp[4] = dp[0] + 1;

dp[5] = dp[1] + 1;

dp[6] = dp[2] + 1;

dp[7] = dp[3] + 1;

dp[8] = dp[0] + 1; ...

This is the function we get, now we need find the other pattern for the function to get the general function. After we analyze the above function, we can get dp[0] = 0;

dp[1] = dp[1-1] + 1;

dp[2] = dp[2-2] + 1;

dp[3] = dp[3-2] + 1;

dp[4] = dp[4-4] + 1;

dp[5] = dp[5-4] + 1;

dp[6] = dp[6-4] + 1;

dp[7] = dp[7-4] + 1;

dp[8] = dp[8-8] + 1; ..

Obviously, we can find the pattern for above example, so now we get the general function

dp[index] = dp[index - offset] + 1;

当我们把数从0到N写出来时很容易发现规律，即对于2^N的数，末尾N-1位的重复规律，正好等于前N-1个数的重复规律，而这时只需要加1即可
*/

public class CountingBits {
  public int[] countBits(int num) {
    int[] res = new int[num + 1];
    res[0] = 0;
    int base = 1; // ending is bit 1	
    while (base <= num) {
			int next = base * 2; // next number ending with all bits 0
			for (int i = base; i<next && i<=num; i++) {
				res[i] = res[i-base] + 1;
			}
			base = next;
		}
    return res;		
  }
}