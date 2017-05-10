/**
* Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
* 
* For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
* 
*     1
*    / \
*   2   2
*  / \ / \
* 3  4 4  3
* But the following [1,2,2,null,3,null,3] is not:
*     1
*    / \
*   2   2
*    \   \
*    3    3
* Note:
* Bonus points if you could solve it both recursively and iteratively.
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
一颗树对称其实就是看左右子树是否对称，一句话就是左同右，右同左，结点是对称的相等
递归结束条件： 假设到了某一结点，不对称的条件有以下三个：
（1）左边为空而右边不为空； （2）左边不为空而右边为空； （3）左边值不等于右边值
根据这几个条件在遍历时进行判断
算法的时间复杂度是树的遍历O(n)，空间复杂度同样与树遍历相同是O(logn)
*/
 
// solution 1: recursive   
public class SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;    
    return helper(root.left, root.right);
  }
	
	private boolean helper(TreeNode rootL, TreeNode rootR) {
		if (rootL == null && rootR == null) {
			return true;
		}
		if (rootL == null || rootR == null) {
			return false;
		}
		if (rootL.val != rootR.val) {
			return false;
		}
		return helper(rootL.left, rootR.right) && helper(rootR.left, rootL.right);
	}
}
 
// solution 2: iterative: using level order traversal     
public class SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;    
    if (root.left == null && root.right == null) return true;
    if (root.left == null || root.right == null) return false;
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();
    Queue<TreeNode> q2 = new LinkedList<TreeNode>();
    q1.add(root.left);
    q2.add(root.right);
    while (!q1.isEmpty() && !q2.isEmpty()) {
  	  TreeNode n1 = q1.poll();
  	  TreeNode n2 = q2.poll();
  	
  	  if (n1.val != n2.val) return false;
  	  if (n1.left == null && n2.right != null || n1.left != null & n2.right == null) return false;
      if (n1.right == null && n2.left != null || n1.right != null && n2.left == null) return false;
      if (n1.left != null && n2.right != null) {
  		  q1.add(n1.left);
  		  q2.add(n2.right);
  	  }
  	  if (n1.right != null && n2.left != null) {
  		  q1.add(n1.right);
  		  q2.add(n2.left);
  	  }
    }
    return true;
  }
}