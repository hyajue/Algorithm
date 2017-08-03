/**
* Given a Binary Tree and a key, write a function that prints all the ancestors of the key in the given binary tree.
* 
* For example, if the given tree is following Binary Tree and key is 7, then your function should print 4, 2 and 1.
* 
* 
*               1
*             /   \
*           2      3
*         /  \
*       4     5
*      /
*     7
*/

/*
复杂度
时间：O(n) 空间：O(logn)

思路：递归
If target is present in either left or right subtree of this node, then add this node into list
*/

public class FindAncestors {
  public List<TreeNode> allAncestors(TreeNode root, int target) {
    List<TreeNode> list = new ArrayList<TreeNode>();
		if (root == null) return list;
		helper(root, target, list);
		return list;
	}	
	
	private boolean helper(TreeNode root, int target, List<TreeNode> list) {
	  if (root == null) return false;
    if (root.val == target) {
      return true;  
		}	
		if (helper(root.left, target, list) || helper(root.right, target, list)) {
		  list.add(root);
      return true;			
		}
		return false;
	}
}