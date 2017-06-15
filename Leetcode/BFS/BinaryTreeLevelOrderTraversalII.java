/**
* Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
* 
* For example:
* Given binary tree [3,9,20,null,null,15,7],
*     3
*    / \
*   9  20
*     /  \
*    15   7
* return its bottom-up level order traversal as:
* [
*   [15,7],
*   [9,20],
*   [3]
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
时间：O(n)		空间：O(n)

思路：BFS
在层序遍历的过程中 每次将本层遍历的结果加入位置0 这样就能实现先遍历的层被后来遍历的层“挤到”后面去
*/ 
 
public class BinaryTreeLevelOrderTraversalII {
  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    if (root == null) return result;
    q.offer(root);
    while (!q.isEmpty()) {
      int size = q.size();
      ArrayList<Integer> level = new ArrayList<Integer>();
      for (int i = 0; i < size; i++) {
        TreeNode cur = q.poll();
        level.add(cur.val);
        if (cur.left != null) q.offer(cur.left);
        if (cur.right != null) q.offer(cur.right);
      }
      result.add(0, level);
      
    }
    return result;
  }
}


