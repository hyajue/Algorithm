/**
* Given a binary tree, return the inorder traversal of its nodes' values.
* 
* For example:
* Given binary tree [1,null,2,3],
*    1
*     \
*      2
*     /
*    3
* return [1,3,2].
* 
* Note: Recursive solution is trivial, could you do it iteratively?
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

思路：栈
利用栈模拟递归过程,先把root压栈,如果root有左子树,则继续把左子树压栈,直到左孩子为空
再弹栈,将弹出的节点设为root,继续重复上述过程
*/
 
public class BinaryTreeInorderTraversal {
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    Stack<TreeNode> nodeStack = new Stack<TreeNode>();
    while (root != null ||　!nodeStack.isEmpty()) {
			if (root != null) {
				nodeStack.push(root);
				root = root.left;
			} else {
				root = nodeStack.pop();
				res.add(root.val);
				root = root.right;
			}
 		}
    return res; 		
	}
}