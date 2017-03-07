/**
* Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
* Each element is either an integer, or a list -- whose elements may also be integers or other lists.
* Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
* Example 1:
* Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
* Example 2:
* Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
*/

/*
solution 1:
先检查最大深度，再计算
*/

public class NestedListWeightSumII {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		if (nestedList == null || nestedList.size() == 0) return 0;
		int maxLevel = getMaxLevel(nestedList, 1);
			
	}
	private int getMaxDepth(List<NestedInteger> nestedList, int depth) {
		int max = depth;
		for (NestedInteger item : nestedList) {
			if (!item.isInteger()) {
				max = Math.max(max, getMaxDepth(item.getList(), depth+1));
			}
		}
		return max;
	}
	private int sum(List<NestedInteger> nestedList, int depth) {
		int sum = 0;
		for (NestedInteger item : nestedList) {
			if (item.isInteger()) {
				sum += item.getInteger() * depth;
			} else {
				sum += sum(item.getList(), depth-1);
			}
		}
		return sum;
	}
}



/** 
 * // This is the interface that allows for creating nested lists. 
 * // You should not implement it, or speculate about its implementation 
 * public interface NestedInteger { 
 * 
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list. 
 *     public boolean isInteger(); 
 * 
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer 
 *     // Return null if this NestedInteger holds a nested list 
 *     public Integer getInteger(); 
 * 
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list 
 *     // Return null if this NestedInteger holds a single integer 
 *     public List<NestedInteger> getList(); 
 * } 
 */  