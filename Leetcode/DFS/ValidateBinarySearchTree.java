/**
* Given a binary tree, determine if it is a valid binary search tree (BST).
* 
* Assume a BST is defined as follows:
* 
* The left subtree of a node contains only nodes with keys less than the node's key.
* The right subtree of a node contains only nodes with keys greater than the node's key.
* Both the left and right subtrees must also be binary search trees.
* Example 1:
*     2
*    / \
*   1   3
* Binary tree [2,1,3], return true.
* Example 2:
*     1
*    / \
*   2   3
* Binary tree [1,2,3], return false.
*/

/*
solution 1:
利用二分查找树的性质，它的中序遍历结果是按顺序递增 
根据这一点我们只需要中序遍历这棵树，然后保存所有节点的值
最后遍历节点值数组 若为不减数组则成立
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
public class ValidateBinarySearchTree {
	public boolean isValidBST(TreeNode root) {
		if (root == null) return true;
		List<Integer> nodeList = new ArrayList<Integer>();
		inOrderTraversal(root, nodeList);
		for (int i = 1; i < nodeList.size(); i++) {
			if (nodeList.get(i) <= nodeList.get(i-1)) {
				return false;
			}
		}
		return true;
	}
	
	private void inOrderTraversal(TreeNode root, List<Integer> nodeList) {
		if (root == null) return;
		inOrderTraversal(root.left, nodeList);
		nodeList.add(root.val);
		inOrderTraversal(root.right, nodeList);
	}
}

/*
soution 2: recursion
*/

public class ValidateBinarySearchTree {
  public boolean isValidBST(ListNode root) {
    return isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
  }
  
  private boolean isValid(ListNode root, int max, int min) {
    if (root == null) return true;
    if (root.val >= max) return false;
    if (root.val <= min) return false;
    return isValid(root.left, root.val, min) && isValid(root.right, max, root.val);
  }
}