/**
* Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
* 
* For example:Given a binary tree {1,2,3,4,5},
* 
*     1
*    / \
*   2   3
*  / \
* 4   5
* return the root of the binary tree [4,5,2,#,#,3,1].
* 
*    4
*   / \
*  5   2
*     / \
*    3   1  
* 	 
*/

/*
solution 1
复杂度
时间O(n) 空间O(n)
思路：递归
关于upside down:
   root      -->     left   
  /    \            /    \
left   right     right   root
其实就是三个节点顺时针旋转 直观滴想 整个树upside down后的根是原树的最左边的叶节点
对给定的例子分析，将最左左子树4-2-5上下颠倒后 4作为新根 与1,3构成新子树结构 4-1-3 此时出现问题
把4旋转到树根后 如何解决1 3及其子树 和4的子树的连接问题 根据所给的例子 意思是把1-3连接到4最右孩子的上面
而且3变成左孩子 1变成右孩子 
*/

public class BinaryTreeUpsideDown {
  public TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null) return root;
		//每层递归中记录当前节点，当前左孩子和当前右孩子，以便递归返回的时候修改连接关系
		TreeNode parent = root;
		TreeNode leftChild = root.left;
		TreeNode rightChild = root.right;
		if (leftChild != null) {
			TreeNode newRoot = upsideDownBinaryTree(left);
			leftChild.left = rightChild;
			leftChild.right = parent;
			return newRoot;
		}
		return root; // if leftChild == null
  }
}  

/*
solution 2
复杂度 
时间O(n) 空间O(1)

思路：迭代 
与解法1思路完全一样 只是表达方式换成迭代法
*/

public class BinaryTreeUpsideDown {
  public TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null) return root;
		TreeNode curNode = root; 
		TreeNode parent = root;
		TreeNode rightChild = null; //为了第一次给根赋新的左孩子
		while (curNode != null) {
			TreeNode leftChild = curNode.left;
			curNode.left = rightChild;
			rightChild = curNode.right;
			curNode.right = parent;
			curNode = leftChild;
		}
		return parent;
  }
}  	 