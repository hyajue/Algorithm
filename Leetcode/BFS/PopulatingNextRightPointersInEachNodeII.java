/**
* Follow up for problem "Populating Next Right Pointers in Each Node".
* 
* What if the given tree could be any binary tree? Would your previous solution still work?
* 
* Note:
* 
* You may only use constant extra space.
* For example,
* Given the following binary tree,
*          1
*        /  \
*       2    3
*      / \    \
*     4   5    7
* After calling your function, the tree should look like:
*          1 -> NULL
*        /  \
*       2 -> 3 -> NULL
*      / \    \
*     4-> 5 -> 7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

/* 
由于条件变成任一二叉树 所以采用层序遍历法解决 T(n) = O(n)
*/
public class PopulatingNextRightPointersInEachNodeII {
  public void connect(TreeLinkNode root) {
    if (root == null) {
		return;
	}
	TreeLinkNode curNode = root; // current level node
	TreeLinkNode nextLevelHead = null;  // next level head node
	TreeLinkNode prev = null;  // next level next node 
	while (curNode != null) {
	  while (curNode != null) {
		if (curNode.left != null) {
		  if (prev != null) {
			prev.next = curNode.left;  
		  } else {
			  nextLevelHead = curNode.left;
		  }
		    prev = curNode.left;	
		}
		if (curNode.right != null) {
		  if (prev != null) {
		    prev.next = curNode.right;  
		  } else {
			  nextLevelHead = curNode.right;
		  }
		  prev = curNode.right;
		}
		curNode = curNode.next;
	  }
	  // move to next level 
	  curNode = nextLevelHead;
	  nextLevelHead = null;
	  prev = null;
	}
  }
}