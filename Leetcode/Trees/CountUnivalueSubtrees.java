/**
* Given a binary tree, count the number of uni-value subtrees.
* 
* A Uni-value subtree means all nodes of the subtree have the same value.
* 
* For example:
* Given binary tree,
* 
*               5
*              / \
*             1   5
*            / \   \
*           5   5   5
*  
* 
* return 4.
*/

/*
复杂度
时间：O(n) 空间：O(logN)

思路：递归
求节点值都一样的所有子树个数，上面例子中元素都是5的子树有3个叶节点，和根节点的右子树,所以返回4

从根节点出发,自顶向下检索.设计一个递归函数,返回以当前节点为根的树是否为unival树
如果root == null，返回true.
如果左右都返回了true，且root.val与左右子树的值相等,则结果+1,返回true.
否则返回false.
*/

public class CountUnivalueSubtrees {
	public int countUnivalSubtree(TreeNode root) {
		if (root == null) return 0;
		int[] res = new int[1];
	  helper(root, res);
		return res[0];
	}
	
	private boolean helper(TreeNode root, int[] res) {
    if (root == null) return true;
    
		boolean left = helper(root.left, res);
		boolean right = helper(root.right, res);
		if (left && right) {
		  if (root.left != null && root.val != root.left.val) {
        return false;
			}			
			if (root.right != null && root.val != root.right.val) {
			  return false;	
			}
			res[0]++;
			return true;
		}
    return false;
	}
}