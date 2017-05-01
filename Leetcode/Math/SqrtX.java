/**
* Implement int sqrt(int x).
* 
* Compute and return the square root of x.
*/

/*
复杂度
时间：O(logx) 空间O(1)

思路：二分法
知道结果的范围，取定左界和右界，然后每次砍掉不满足条件的一半，直到左界和右界相遇
*/

public class SqrtX {
  public int mySqrt(int x) {
    if (x < 0) return -1;
    if (x == 0) return 0;
    int left = 1;
    int right = x/2 + 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
	    if (mid <= x/mid && x/(mid+1) < (mid+1)) {
	      return mid;
	     } else if (x/mid < mid) {
		     right = mid - 1;
	     } else {
		     left = mid + 1;
	    }
    }
    return 0;	
  }
}