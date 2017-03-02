/**
* Two elements of a binary search tree (BST) are swapped by mistake.
* 
* Recover the tree without changing its structure.
* 
* Note:
* A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
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
solution 1:
递归中序遍历，保存三个指针：两个是要被交换的节点first、second，一个是当前遍历到结点的前序节点pre 
如果当前遍历到的前序节点值大于等于当前节点则该节点存在问题，需要交换
注意如果是发现的第一个有问题的节点，那么需要交换的节点为pre；如果是第二次发现的有问题的节点，
那么需要被交换的节点是当前节点cur。
这个解法比较concise 其空间复杂度是O(logN) 因为是递归 
*/ 

 public class RecoverBinarySearchTree {
    TreeNode firstNode = null;
	TreeNode secNode = null;
	TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
	public void recoverTree(TreeNode root) {
        if (root == null) return;
		inOrder(root);
		int tmp = firstNode.val;
		firstNode.val = secNode.val;
		secNode.val = tmp;
		return;
	}
	private void inOrder(TreeNode root) {
		if (root == null) return;
		inOrder(root.left);
		if (preNode.val >= root.val) {
			if (firstNode == null) {
				firstNode = preNode;
			}
			secNode = root;
		}
		preNode = root;
		inOrder(root.right);
	}
} 


/*
solution 2:
Morris Traversal: 线索二叉树
中序遍历原理：
1. 如果当前节点cur的左孩子为空，则输出当前节点并将其右孩子作为当前节点。

2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。

   a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。

   b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。

3. 重复以上1、2直到当前节点为空。
*/

 public class RecoverBinarySearchTree {
	public void recoverTree(TreeNode root) {
		if (root == null) return;
		TreeNode pre = null;
		TreeNode cur = root;
		TreeNode firstNode = null;
		TreeNode secNode = null;
		while (cur != null) {
			// get two nodes to be swapped 
			if (pre != null && pre.val >= cur.val) {
				if (firstNode == null) {
					firstNode = pre;
				}
				secNode = cur;
			}
			if (cur.left != null) {
				TreeNode tmp = cur.left;
				while (tmp.right != null && tmp.right != cur) {
					tmp = tmp.right;
				}
				if (tmp.right == cur) {
					tmp.right = null;
					pre = cur;
					cur = cur.right;
				} else {
				    tmp.right = cur;
					cur = cur.left;
				}
			} else {
				pre = cur; 
				cur = cur.right;
			}
		}
		int tmp = firstNode.val;
		firstNode.val = secNode.val;
		secNode.val = tmp;
	}
} 












