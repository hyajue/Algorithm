/**
* Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
* 
* If two nodes are in the same row and column, the order should be from left to right.
* 
* Examples:
* Given binary tree [3,9,20,null,null,15,7],
* 
*     3
*    / \
*   9  20
*     /  \
*    15   7
*  
* 
* return its vertical order traversal as:
* 
* [
*   [9],
*   [3,15],
*   [20],
*   [7]
* ]
*  
* 
* Given binary tree [3,9,20,4,5,2,7],
* 
*     _3_
*    /   \
*   9    20
*  / \   / \
* 4   5 2   7
*  
* 
* return its vertical order traversal as:
* 
* [
*   [4],
*   [9],
*   [3,5,2],
*   [20],
*   [7]
* ]
*/

/*
复杂度
时间：O(n) 空间O(n)

思路：Map+BFS
在BFS过程里，以列为键值，将节点值保存在Map的值映射中。最后遍历一遍列表 以列从小到大的顺序
取出对应的节点列表 
*/

public class BinaryTreeVerticalOrderTraversal {
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		
		if (root == null) return res;
		
		//level order traversal the tree and keep tack of the colunm index using Map
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> column = new LinkedList<Integer>();
		queue.offer(root);
		column.offer(0);
		int minCol = 0;
		int maxCol = 0;
		while (!queue.isEmpty()) {
			TreeNode curNode = queue.poll();
			int curIdx = column.poll();
			if (!map.containsKey(curIdx)) {
				map.put(curIdx, new ArrayList<Integer>());
			}
			map.get(curIdx).add(curNode.val);
			//此时我们并不知道最小列和最大列分别是几 所以需要维护两个标量去记录
			minCol = Math.min(minCol, curIdx);
			maxCol = Math.max(maxCol, curIdx);
			
			//左右子节点和相应的列数分别入队
			if (curNode.left != null) {
				queue.offer(curNode.left);
				column.offer(curIdx-1);
			}
			if (curNode.right != null) {
				queue.offer(curNode.right);
				column.offer(curIndx+1);
			}
		}
		
		for (int i = minCol; i <= maxCol; i++) {
			if (map.containsKey(i)) {
				res.add(map.get(i));
			}
		}
		return res;
	}
} 






















