/**
* Given a binary tree, find its minimum depth.
* 
* The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
solution 1: recursive
复杂度
时间：O(n) 空间：O(logn)

思路：
跟Maximum Depth of Binary Tree非常类似，只是这道题因为是判断最小深度，
所以必须增加一个叶子的判断（因为如果一个节点如果只有左子树或者右子树，我们不能取它左右子树中小的作为深度，因为那样会是0，
我们只有在叶子节点才能判断深度，而在求最大深度的时候，因为一定会取大的那个，所以不会有这个问题）
比Maximum Depth of Binary Tree多加一个左右子树的判断
*/ 
 
public class MinimumDepthOfBinaryTree {
  public int minDepth(TreeNode root) {
    if (root == null) {
  	  return 0;
    }  
    if (root.left == null) {
  	  return minDepth(root.right) + 1;
    }
    if (root.right == null) {
  	  return minDepth(root.left) + 1;
    }
    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
  }
}