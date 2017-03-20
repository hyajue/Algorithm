/*
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
*/

/*
复杂度
时间 O(logN) 空间 O(1)

思路:二分查找
在升序的引用数数组中，假设数组长为N，下标为i，则N - i就是引用次数大于等于下标为i的文献所对应的引用次数的文章数。如果该位置的引用数小于文章数，则说明则是有效的H指数，
如果一个数是H指数，那最大的H指数一定在它的后面（因为升序） 根据这点就可已进行二分搜索了。这里lo = mid + 1的条件是citations[mid] < len - mid，确保退出循环时lo肯定是指向一个有效的H指数。
*/

public class HIndexII {
  public int hIndex(int[] citations) {
    int len = citations.length;
	if (len == 0) return 0;
	int lo = 0;
	int hi = len - 1;
	while (lo <= hi) {
	  int mid = lo + (hi - lo) / 2;
	  if (citations[mid] < len - mid) {
		lo = mid + 1;
	  } else {
		hi = mid - 1;
	  }
	}
	// length - lo is the H-index of lo posititon
	return len - lo; 
  }
}
