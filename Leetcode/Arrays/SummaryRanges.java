/**
* Given a sorted integer array without duplicates, return the summary of its ranges.
* 
* For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

/*
给定排序数组 
N<a1,a2,a3,...,an>,ai<ai+1,0≤i≤n-1， 
如果N为空或者没有任何数，直接返回空元素List 
如果N不为空，如果ai+1≠a[i+1]，则ai一定为某个范围起点，a[i+1]一定为某个范围终止点。

特殊考虑 
- 第一个元素一定是某个起点，最后一个元素一定是某个范围的终点。 
- 如果起点和终点相同，不需要打印箭头

扫描一遍数组：
T(n) = O(n) 
*/

public class SummaryRanges {
  public List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<String>();
    if (nums == null || nums.length == 0) {
    	return res;
    }
    
    for (int i = 0; i < nums.length; i++) {
    	int front = nums[i];
    	String frontStr = Integer.toString(front);
    	
			while (i+1 < nums.length && nums[i+1] == nums[i]+1) {
    		i++;
    	}
			
    	if (nums[i] != front) {
    		res.add(frontStr + "->" + Integer.toString(nums[i]));
    	} else {
    		res.add(frontStr);
    	}
			
    }
    return res;
  }
} 
