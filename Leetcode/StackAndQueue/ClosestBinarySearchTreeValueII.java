/**
* Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
* 
* Note: Given target value is a floating point. You may assume k is always valid, that is: k ≤ total nodes. You are guaranteed to have only one unique set of k values in the BST that are closest to the target. Follow up: Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
* 
* Hint:
* 
* Consider implement these two helper functions: getPredecessor(N), which returns the next smaller node to N. getSuccessor(N), which returns the next larger node to N.
*/

/*
复杂度
时间：O(N) 空间：Max(O(K), O(H))

思路:中序遍历
二叉搜索树的中序遍历就是顺序输出二叉搜索树，所以我们只要中序遍历二叉搜索树，同时维护一个大小为K的队列，前K个数直接加入队列，之后每来一个新的数（较大的数），
如果该数和目标的差，相比于队头的数离目标的差来说，更小，则将队头拿出来，将新数加入队列。如果该数的差更大，则直接退出并返回这个队列，因为后面的数更大，差值也只会更大。
*/

public class Solution {
  public List<Integer> closestKValues(TreeNode root, double target, int k) { 
    Queue<Integer> queue = new LinkedList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    while (root != null) {
			stack.push(root);
			root = root.left;
		}		
		while (!stack.isEmpty()) {
		  TreeNode cur = stack.pop();
      if (queue.size() < k) {
				queue.offer(cur.val);
			} else {
				// 队列里面数量到K时,判断下个新数是否更接近,更接近的话就加入队列并去掉队头元素
				int first = queue.peek();
				if (Math.abs(first - target) > Math.abs(cur.val - target)) {
					queue.poll();
					queue.offer(cur.val);
				} else {
					break;
				}
			}
      if (cur.right != null) {
				cur = cur.right;
				while (cur != null) {
					stack.push(cur);
					cur = cur.left;
				}
			}			
		}
		return (List<Integer>)queue;
  } 
}