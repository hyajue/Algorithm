/**
* Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
* 
* For example:
* Given binary tree [3,9,20,null,null,15,7],
*     3
*    / \
*   9  20
*     /  \
*    15   7
* return its zigzag level order traversal as:
* [
*   [3],
*   [20,9],
*   [15,7]
* ]
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

思路：BFS
使用队列实现蛇形遍历，发现奇数行的遍历仍然可以按照广度优先遍历的方式实现，
而对于偶数行，只需要翻转一下
*/
 
public class BinaryTreeZigzagLevelOrderTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (root == null) return res;
    boolean odd = true;
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    while(!queue.isEmpty()) { 
	    // level order traversal
      int queueLen = queue.size();
      List<Integer> level = new ArrayList<Integer>();
      for(int i = 0; i < queueLen; i++) {
		    TreeNode node = queue.poll();
		    level.add(node.val);
		    if(node.left != null) queue.offer(node.left);
		    if(node.right != null) queue.offer(node.right);
	    }
      // reverse the even level of list
      if(odd) {
		    res.add(level);
	    } else {
		    Collections.reverse(level);
        res.add(level);		  
	    }	  
	    odd = !odd;
	  }
    return res;    
  }
} 
