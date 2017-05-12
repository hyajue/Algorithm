/**
* Given a binary tree, find its maximum depth.
* 
* The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
*/
 
public class MaximumDepthOfBinaryTree {
  public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    int leftMax = maxDepth(root.left);
    int rightMax = maxDepth(root.right);
    return Math.max(leftMax, rightMax) + 1;
  }
}
 
/*
solution 2: iterative 
using level order traversal 
*/

public class MaximumDepthOfBinaryTree {
  public int maxDepth(TreeNode root) {
  if (root == null) return 0;
  int depth = 0;
  Queue<TreeNode> queue = new LinkedList<TreeNode>();
  queue.add(root);
  int curNum = 1; // num of nodes in current level
  int nextNum = 0; // num of nodes in next level
  while (!queue.isEmpty()) {
  	TreeNode node = queue.poll();
  	curNum--;
  	if (node.left != null) {
  		queue.add(node.left);
  		nextNum++;
  	}
  	if (node.right != null) {
  		queue.add(node.right);
  		nextNum++;
  	}
  	if (curNum == 0) {
  		curNum = nextNum;
  		nextNum = 0;
  		depth++;
  	}
  }
  return depth;
  }
}