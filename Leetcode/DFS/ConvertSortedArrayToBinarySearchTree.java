/**
* Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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
时间：O(n) 空间：O(n)

思路：
从本质来看，如果把一个数组看成一棵树(也就是以中点为根，左右为左右子树，依次下去)数组就等价于一个二分查找树
所以如果要构造这棵树，那就是把中间元素转化为根，然后递归构造左右子树 所以用二叉树递归的方法来实现.
以根作为返回值，每层递归函数取中间元素，作为当前根和赋上结点值，然后左右结点接上左右区间的递归函数返回值
时间复杂度是一次树遍历O(n,总的空间复杂度是栈空间O(logn)加上结果的空间O(n)，额外空间是O(logn)，总体是O(n) 
*/

public class ConvertSortedArrayToBinarySearchTree {
  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums == null || nums.length == 0) return null;
    return helper(nums, 0, nums.length-1);
  }
	
	private TreeNode helper(int[] nums, int left, int right) {
		if (left > right) return null;
		int mid = (left + right) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = helper(nums, left, mid-1);
		root.right = helper(nums, mid+1, right);
		return root;
	}
} 
