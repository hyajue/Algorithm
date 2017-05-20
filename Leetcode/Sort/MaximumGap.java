/**
* Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
* 
* Try to solve it in linear time/space.
* 
* Return 0 if the array contains less than 2 elements.
* 
* You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

/*
复杂度
时间：O(n) 空间O(n)

思路：桶排序
这题要求O（N）的时间和空间复杂度，显然不能使用quick sort，merge sort， heap sort这类基于比较的排序算法，因为是O（Nlog（N））的时间复杂度。
于是考虑使用bucket sort，radix sort，counting sort的思路->这里选择桶排序
假设num数组中最大值和最小值为max和min，那么我们可以令每个桶的大小为bukitLength = Math.ceil((max - min) / (double) len)，
定义(max -  min) / bukitLength  + 1个桶，就可以保证存下从min到max这个range内的所有值
桶定义好之后，就可以根据元素大小放入桶中。注意max gap不可能存在于同一个桶的元素之间，因为同一个桶的元素之间gap至多只能是bukitLength-1，
如果max gap是某两个同一个桶中的元素，那么这些数的range一定比max - min要小，出现矛盾。所以，对于数组中的任意整数nums[i]，可以通过公式loc = (K - min) / bukitLength找出其桶的位置，
然后维护每一个桶的最大值和最小值。对于每一个非空的桶p，找出下一个非空的桶q，则q.min - p.max可能就是备选答案。返回这些备选答案中最大的一个即可
*/

public class MaximumGap {
  public int maximumGap(int[] nums) {
    if (nums == null || nums.length < 2) return 0;
		int len = nums.length;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[i]);
		}
		
		int maxDiff = max - min; 
		if (maxDiff == 0) {
		    return 0;
		}
		//定义桶大小
		int bukitLen = (int)Math.ceil(maxDiff/(double)(len));
		//定义桶个数 注意这里多定义一个桶 防止最大的元素所在的桶index越界
		int bukitNum = maxDiff/bukitLen + 1;
		
		//存放每个桶的最大值和最小值
		List<Integer> bktMax = new ArrayList<Integer>();
		List<Integer> bktMin = new ArrayList<Integer>();
		
		//初始化每个桶的最大最小值
		for (int i = 0; i < bukitNum; i++) {
			bktMin.add(Integer.MAX_VALUE);
			bktMax.add(Integer.MIN_VALUE);
		}
		
		//将各元素放入相应桶中
		for (int i = 0; i < len; i++) {
			int bukitIdx = (nums[i]-min) / bukitLen;
			bktMax.set(bukitIdx, Math.max(nums[i], bktMax.get(bukitIdx)));
			bktMin.set(bukitIdx, Math.min(nums[i], bktMin.get(bukitIdx))); 
		}
		
		int prevMax = min;
		int maxGap = Integer.MIN_VALUE;
		
		for (int i = 0; i < bukitNum; i++) {
			if (bktMax.get(i) == Integer.MIN_VALUE || bktMin.get(i) == Integer.MAX_VALUE) {
				//empty bucket, skip 
				continue;
			}
			maxGap = Math.max(maxGap, bktMin.get(i)-prevMax);
			prevMax = bktMax.get(i);
		}
		return maxGap;
  }
} 