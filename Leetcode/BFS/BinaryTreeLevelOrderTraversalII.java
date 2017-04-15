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
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) return res;
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int curLevelNum = 1;
		int nxtLevelNum = 0;
		while (curLevelNum != 0) {
			List<Integer> curLevelRes = new ArrayList<Integer>();
			nxtLevelNum = 0;
			while (curLevelNum != 0) {
				TreeNode curNode = queue.poll();
				curLevelNum--;
				curLevelRes.add(curNode.val);
				if (curNode.left != null) {
					queue.offer(curNode.left);
					nxtLevelNum++;
				}
				if (curNode.right != null) {
					queue.offer(curNode.right);
					nxtLevelNum++;
				}
			}
			res.add(0, curLevelRes);
			curLevelNum = nxtLevelNum;
		}
		return res;
	}
}