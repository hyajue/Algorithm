/**
* Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
* 
* For example:
* Given the below binary tree and sum = 22,
*               5
*              / \
*             4   8
*            /   / \
*           11  13  4
*          /  \      \
*         7    2      1
* return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
用常规的递归方法来做，递归条件是看左子树或者右子树有没有满足条件的路径，也就是子树路径和等于当前sum减去当前节点的值。
结束条件是如果当前节点是空的，则返回false，如果是叶子，那么如果剩余的sum等于当前叶子的值，则找到满足条件的路径，返回true。
算法的复杂度是树的遍历，时间复杂度是O(n)，空间复杂度是O(logn)
*/
 
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
			return false;
		}
		if (root.left == null && root.right ==null && root.val == sum) {
			return true;
		}
		return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}

 

