/**
* Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
* 
* Note:
* Given target value is a floating point.
* You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

/*
复杂度
时间 O(logN) 空间 O(H), H is the height of tree

思路:递归
根据二叉树的性质，我们知道当遍历到某个根节点时，最近的那个节点要么是在子树里面，要么就是根节点本身。
所以我们根据这个递归，返回子树中最近的节点，和根节点中更近的那个就行了。
*/

public class ClosestBinarySearchTreeValue {
  public int closestValue(TreeNode root, double target) {
    TreeNode subRoot = null;
	// pick up one of subtrees
    if (target < root.val) {
	  subRoot = root.left;
	} else {
	  subRoot = root.right;
	}	
	// no children -> recursion hits leaf
	if (subRoot == null) {
	  return root.val;
	}
	// find closest val recursively
    int closest = closestValue(subRoot, target);
    // 
    double diffToRoot = Math.abs(root.val - target);
    double diffToSubRoot = Math.abs(closest - target);
    if (diffToRoot < diffToSubRoot) {
	  return root.val;
	} else {
	  return closest;
	}	
  }
} 
