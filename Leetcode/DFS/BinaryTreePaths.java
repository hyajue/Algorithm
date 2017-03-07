/**
* Given a binary tree, return all root-to-leaf paths.
* 
* For example, given the following binary tree:
* 
*    1
*  /   \
* 2     3
*  \
*   5
* All root-to-leaf paths are:
* 
* ["1->2->5", "1->3"]
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
一道挺简单的Tree的DFS输出路径的题目，但是题目唯一tricky的地方就是输出 
它是要输出一个String类型的list，并不是像往常一样输出一个Integer类型的Array
在Java中，String的‘+’操作符是javaJDK中唯一overload的操作符 
它其实是完成了append()方法，所以使用‘+’操作符进行字符串链接时效率并没有那么高,此题选用StringBuilder提高效率
*/ 
 
 public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
		if (root == null) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		helper(root, sb, res);
		return res;
    }
	private void helper(TreeNode root, StringBuilder sb, List<String> res) {
		if (root == null) {
			return;
		}
		sb.append(String.valueOf(root.val));
		int length = sb.length();
		if (root.left == null && root.right == null) {
			res.add(sb.toString());
			return;
		}
		if (root.left != null) {
			helper(root.left, sb.append("->"), res);
			// sb stores the last solution added in res, we have to remove it first 
			// before we go to another branch
			sb.delete(length, sb.length());
		}
		if (root.right != null) {
			helper(root.right, sb.append("->"), res);
			sb.delete(length, sb.length());
		}
	}
}






























 
