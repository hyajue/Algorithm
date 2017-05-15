/**
* Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
* 
* An example is the root-to-leaf path 1->2->3 which represents the number 123.
* 
* Find the total sum of all root-to-leaf numbers.
* 
* For example,
* 
*     1
*    / \
*   2   3
* The root-to-leaf path 1->2 represents the number 12.
* The root-to-leaf path 1->3 represents the number 13.
* 
* Return the sum = 12 + 13 = 25.
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
复杂度
时间:O(n) 空间：O(logN)

思路：
树的题目，一般使用递归来做，主要就是考虑递归条件和结束条件。
目标是把从根到叶子节点的所有路径得到的整数都累加起来，递归条件即是把当前的sum乘以10并且加上当前节点传入下一函数，进行递归，
最终把左右子树的总和相加。结束条件的话就是如果一个节点是叶子，那么我们应该累加到结果总和中，如果节点到了空节点，则不是叶子节点，
不需要加入到结果中，直接返回0即可。算法的本质是一次先序遍历，所以时间是O(n)，空间是栈大小，O(logn)
*/ 
 
public class SumRootToLeafNumbers {
  public int sumNumbers(TreeNode root) {
    return helper(root, 0);
  }
	
	private int helper(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return (root.val + sum * 10);
		}
		return helper(root.left, root.val + sum*10) + helper(root.right, root.val + sum*10);
	}
} 
