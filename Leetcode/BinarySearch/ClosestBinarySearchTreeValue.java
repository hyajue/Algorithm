/**
* Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
* 
* Note:
* Given target value is a floating point.
* You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

/*
solution 1
复杂度
时间 O(logn) 空间 O(h), h is the height of tree

思路:递归
当遍历到某个根节点时，比较该节点的值与目标值，有两种情况：
1. 最近节点为该节点本身
2. 最近节点在该节点的子树里面
分别求出子树里面的最近值和该节点的值 比较哪一个跟接近目标值
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
 
    double diffToRoot = Math.abs(root.val - target);
    double diffToSubRoot = Math.abs(closest - target);
    if (diffToRoot < diffToSubRoot) {
			return root.val;
		} else {
			return closest;
		}	
  }
} 


/*
solution 2
复杂度
时间O(logn) 空间O(1)

思路：iterative迭代
维护一个与目标最近的值 然后迭代二叉搜索的过程中不停更新这个值
*/

public class ClosestBinarySearchTreeValue {
  public int closestValue(TreeNode root, double target) {
		int closest = root.val;
		while (root != null) {
			int diff1 = Math.abs(closest - target);
			int diff2 = Math.abs(root.val - target);
			//更新最近值如果该节点离目标值更近,否则不变
			if (diff2 < diff1) {
				closest = root.val;
			}
			//左右子树搜索
			if (target < root.val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return closest;
  }
} 
