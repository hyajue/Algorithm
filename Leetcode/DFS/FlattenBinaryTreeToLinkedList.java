/**
* Given a binary tree, flatten it to a linked list in-place.
* 
* For example,
* Given
* 
*          1
*         / \
*        2   5
*       / \   \
*      3   4   6
* The flattened tree should look like:
*    1
*     \
*      2
*       \
*        3
*         \
*          4
*           \
*            5
*             \
*              6
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
用递归来解决，维护先序遍历的前一个结点pre，然后每次把pre的左结点置空，右结点设为当前结点
这里需要注意的一个问题就是我们要先把右子结点保存一下，以便等会可以进行递归，
否则有可能当前结点的右结点会被覆盖，后面就取不到了。算法的复杂度时间上还是一次遍历，O(n)。空间上是栈的大小，O(logn)
*/ 
 
public class FlattenBinaryTreeToLinkedList {
  public void flatten(TreeNode root) {
    if (root == null) return;
    List<TreeNode> prev =  new ArrayList<TreeNode>();
    prev.add(null);
	helper(prev, root);
  }
  private void helper(List<TreeNode> prev, TreeNode root) {
    if(root == null) return;
	TreeNode right = root.right;
	if(prev.get(0) != null) {
	  prev.get(0).left = null;
      prev.get(0).right = root;	  
	}
	prev.set(0,root);
	helper(prev, root.left);
	helper(prev, right);
  }
} 