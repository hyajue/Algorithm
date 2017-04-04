/**
* Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.
* 
*  Notice
* 
* You can assume there is no duplicate values in this tree + node.
* 
* Example
* Given binary search tree as follow, after Insert node 6, the tree should be:
* 
*   2             2
*  / \           / \
* 1   4   -->   1   4
*    /             / \ 
*   3             3   6
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
/*
复杂度
时间：O(h) h is the height of the BST  空间:O(1)

思路：迭代
根据BST性质：左子树所有节点比当前节点值小，右子树所有节点比当前节点值大。以此来判定搜素范围  
*/

public class InsertNodeInABinarySearchTree {
	/**
	* @param root: The root of the binary search tree.
	* @param node: insert this node into the binary search tree
	* @return: The root of the new binary search tree.
	*/
	public TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) return node;
		if (node == null) return root;
		
		TreeNode nodePtr = root;
		while (root != null) {
			if (node.val < root.val) {
				if (root.left == null) { //hits leaf
					root.left = node;
					break;//插入节点后退出while循环
				} else {
					root = root.left;
				}
			} else if (node.val > root.val) {
				if (root.right == null) { //hits leaf 
					root.right = node;
					break;//插入节点后退出while循环
				} else {
					root = root.right;
				}
			}
		}
		return nodePtr;
	}
}
