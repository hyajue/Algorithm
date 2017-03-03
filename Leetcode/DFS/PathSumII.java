/**
* Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
* 
* For example:
* Given the below binary tree and sum = 22,
*               5
*              / \
*             4   8
*            /   / \
*           11  13  4
*          /  \    / \
*         7    2  5   1
* return
* [
*    [5,4,11,2],
*    [5,8,4,5]
* ]
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/* 
跟Path Sum的要求很接近，都是寻找从根到叶子的路径。这道题目的要求是求所有满足条件的路径，
所以我们需要数据结构来维护中间路径结果以及保存满足条件的所有路径。这里的时间复杂度仍然只是一次遍历O(n)，
而空间复杂度则取决于满足条件的路径和的数量（假设是k条），则空间是O(klogn) 
*/ 
  
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) {
			return res;
		}
		List<Integer> items = new ArrayList<Integer>();
		item.add(root.val);
		helper(root, sum-root.val, items, res);
		return res;
    }
	private void helper(TreeNode root, int sum, List<Integer> items, List<List<Integer>> res) {
		if (root == null) return;
		if (root.left == null && root.right == null && sum == 0) {
			res.add(new ArrayList<Integer>(items));
			return;
		}
		if (root.left != null) {
			items.add(root.left.val);
			helper(root.left, sum-root.left.val, items, res);
			items.remove(items.size()-1); // backtracking remove the least recent added item if search fails
		}
		if (root.right != null) {
			items.add(root.right.val);
			helper(root.right, sum-root.right.val, items, res);
			item.remove(items.size()-1);
		}
	}
}




 

