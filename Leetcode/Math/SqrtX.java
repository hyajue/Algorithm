/**
* Implement int sqrt(int x).
* 
* Compute and return the square root of x.
*/

/*
基本思路是跟二分查找类似，要求是知道结果的范围，取定左界和右界，然后每次砍掉不满足条件的一半，
知道左界和右界相遇。算法的时间复杂度是O(logx)，空间复杂度是O(1)
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