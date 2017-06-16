/**
* Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
* 
* Example:Given binary tree
* 
*       1
*      / \
*     2   3
*    / \     
*   4   5    
* Returns [4, 5, 3], [2], [1].
* 
* Explanation:
* 
* Removing the leaves [4, 5, 3] would result in this tree:
*  1
* / 
* 2
* Now removing the leaf [2] would result in this tree:
* 1
* Now removing the leaf [1] would result in the empty tree:
* []
* Returns [4, 5, 3], [2], [1].
*/

/*
先检查最大深度，再根据节点所处的深度放进结果
每一个节点从左子节点和右子节点分开走可以得到两个深度，由于成为叶节点的条件是左右子节点都为空，
所以我们取左右子节点中较大值加1为当前节点的深度值，知道了深度值就可以将节点值加入到结果res中的正确位置了
*/ 
 
public class FindLeavesOfBinaryTree {
  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
	if (root == null) {
		return res;
	}
	helper(res, root);
	return res;
  }
  private int helper(List<List<Integer>> res, TreeNode root) {
    if (root == null) {
		return -1;
	}
    int left = helper(res, root.left);
	int right = helper(res, root.right);
	int height = Math.max(left, right) + 1;
	if (res.size() == height) {
		res.add(new ArrayList<Integer>()); // 没有重复节点 所以可以直接这么写
	}
	res.get(height).add(root.val);
	return height;
  }
}



























