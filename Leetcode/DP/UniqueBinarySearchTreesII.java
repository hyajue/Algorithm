/**
* Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
* 
* For example,
* Given n = 3, your program should return all 5 unique BST's shown below.
* 
*    1         3     3      2      1
*     \       /     /      / \      \
*      3     2     1      1   3      2
*     /     /       \                 \
*    2     1         2                 3
* 
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
时间：指数级别 空间：O(n)

思路：深搜
求所有可能性,首先考虑深搜
每层递归的循环中：
1.生成左子树
2.生成右子数
3.把左右子数接到根节点i上
*/
 
public class UniqueBinarySearchTreesII {
  public List<TreeNode> generateTrees(int n) {
    List<TreeNode> res = new ArrayList<TreeNode>();
		if (n <= 0) return res;
    return helper(1, n);	 	
  }
	
	private List<TreeNode> helper(int start, int end) {
    List<TreeNode> res = new ArrayList<TreeNode>();
		if (start > end) {
		  res.add(null);
      return res;			
		}
    for (int i = start; i <= end; i++) {
			List<TreeNode> left = helper(start, i-1);
			List<TreeNode> right = helper(i+1, end);
			//将生成好的左右子数接到根节点上面
			for (TreeNode leftChild : left) {
				for (TreeNode rightChild : right) {
					TreeNode root = new TreeNode(i);
					root.left = leftChild;
					root.right = rightChild;
					res.add(root);
				}
			}
		}
    return res;		
	}
}