/**
* Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
* 
* Each element is either an integer, or a list -- whose elements may also be integers or other lists.
* 
* Example 1:
* Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
* 
* Example 2:
* Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
*/
 
/*
solution 1: recursive
这道题定义了一种嵌套链表的结构，链表可以无限往里嵌套，规定每嵌套一层，深度加1，让我们求权重之和，就是每个数字乘以其权重，再求总和
那么我们考虑，由于嵌套层数可以很大，所以我们用深度优先搜索DFS会很简单，每次遇到嵌套的，递归调用函数，一层一层往里算就可以了
*/

public class NestedListWeightSum {
    
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return 0;
		return helper(nestedList, 1);
    }
	private int helper(List<NestedInteger> nestedList, int depth) {
		if (nestedList == null || nestedList.size() == 0) return 0;
		int sum = 0;
		for (NestedInteger item : nestedList) {
			if (item.isInteger()) {
				sum += item.getInteger() * depth;
			} else {
				sum += helper(item.getList(), depth+1);
			}
		}
		return sum;
	}
}