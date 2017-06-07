/**
* Given an array of integers A and let n to be its length.
* 
* Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:
* 
* F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
* 
* Calculate the maximum value of F(0), F(1), ..., F(n-1).
* 
* Note:
* n is guaranteed to be less than 10^5.
* 
* Example:
* 
* A = [4, 3, 2, 6]
* 
* F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
* F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
* F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
* F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
* 
* So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
*/

/*
复杂度
时间：O(n) 空间：O(1)

思路：dp + 找规律
对比给出的示例，F(0)与F(1)的区别，就是F(0)中除了A[n-1]之外，其他的数字都增加了一倍，
并且还要再减去（n-1）*A[n-1]，F[0]就是增加了4，3，2，然后再减去了3*6，F[1] = F[0] + 4 + 3 + 2 - 3 * 6 = 16
因此可以求出这样一个公式： 
F[n] = F[n - 1] + (sum - A[sumIndex]) - (A.length - 1) * A[sumIndex] 
其中，sumIndex = -n + A.length，即旋转时要从索引n-1移动到0的那个值 
sum为数组A中所有值的和，而(sum - A[sumIndex]) 自然就是数组A中除了索引为sumIndex之外其他所有值的和 
*/

public class RotateFunction {
  public int maxRotateFunction(int[] A) {
    if (A == null || A.length == 0) return 0;
    int sum = 0;
    for (int num : A) {
			sum += num;
		}	
    int base = 0;
    // calculate F[0]
    for (int i = 0; i < A.length; i++) {
			base += i * A[i];
		}		
		int res = base;
		for (int i = 1; i < A.length; i++) {
			int sumIdx = A.length - i;
			base += sum - A.length * A[sumIdx];
			res = Math.max(res, base);
		}
		return res;
  }
}