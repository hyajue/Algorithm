/**
* Given a complete binary tree, count the number of nodes.
* 
* Definition of a complete binary tree from Wikipedia:
* In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
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
 
 
public class CountCompleteTreeNodes {
  public int countNodes(TreeNode root) {
    if (root == null) return 0;
    int leftHeight = getLeftHeight(root);
	int rightHeight = getRightHeight(root);
	
    if (leftHeight == rightHeight) {
	  return (1 << leftHeight) - 1;
	} else {
	  return 1 + countNodes(root.left) + countNodes(root.right);
	}
  }
  private int getLeftHeight(TreeNode root) {
    if (root == null) return 0;
	int left = getLeftHeight(root.left);
	return left + 1;
  }
  private int getRightHeight(TreeNode root) {
    if (root == null) return 0;
	int right = getRightHeight(root.right);
	return right + 1;
  }
}