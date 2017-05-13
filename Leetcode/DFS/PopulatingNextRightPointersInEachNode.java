/**
* Given a binary tree
* 
*     struct TreeLinkNode {
*       TreeLinkNode *left;
*       TreeLinkNode *right;
*       TreeLinkNode *next;
*     }
* Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
* 
* Initially, all next pointers are set to NULL.
* 
* Note:
* 
* You may only use constant extra space.
* You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
* For example,
* Given the following perfect binary tree,
*          1
*        /  \
*       2    3
*      / \  / \
*     4  5  6  7
* After calling your function, the tree should look like:
*          1 -> NULL
*        /  \
*       2 -> 3 -> NULL
*      / \  / \
*     4->5->6->7 -> NULL
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
复杂度
时间：O(n) 空间：O(1)

思路：
假设curNode在第n层，perNode就在n-1层，它们都是最左边的node，这个时候只需要把curNode一步一步的往右走，
在走的同时填充next属性即可，isLeft变量用来表示该curNode是左孩子还是右孩子，当它是左孩子时，preNode也要往右走
*/ 

public class PopulatingNextRightPointersInEachNode {
  public void connect(TreeLinkNode root) {
    if (root == null) return;
	  root.next = null;
	  TreeLinkNode preNode = root;
	  TreeLinkNode curNode = root.left;
	  while (curNode != null) {
	    TreeLinkNode tmp = curNode;
	    boolean isLeft = true;
	    while(true) {
	      if (isLeft) {
	 	    curNode.next = preNode.right;
	 	    curNode = preNode.right;
	 	    preNode = preNode.next;	
	 	    isLeft = false;	
	      } else {
		      if (preNode == null) {
		        curNode.next = null;
		        break;
		      }
	 	    curNode.next = preNode.left;
	 	    curNode = preNode.left;
	 	    isLeft = true;
	      }
	    }
	  preNode = tmp;
	  curNode = preNode.left;
	  }	
  }  
}