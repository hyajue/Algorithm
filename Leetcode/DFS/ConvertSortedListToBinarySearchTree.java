/**
* Given a singly linked list where elements are sorted in ascending order, 
* convert it to a height balanced BST.
*/ 

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
自底向上的方法，算法复杂度为O(N)。先递归构建左子树，在构建左子树的同时不断移动链表的头指针，
链表的头指针永远是对应当前子树位置 一直到左叶子节点，左叶子节点对应的就是链表的第一个元素，
生成左叶子节点之后移动链表当前指针
*/ 
public class ConvertSortedListToBinarySearchTree {
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
		return null;
	}           
	ListNode curHead = head;
	int len = 0;
	while (curHead != null) {
		curHead = curHead.next;
		len++;
	}
	List<ListNode> list = new ArrayList<>();
	list.add(head);
	TreeNode root = helper(list, 0, len-1);
	return root;
  }
  private TreeNode helper(List<ListNode> list, int idxL, int idxR) {
	  if (idxL > idxR) {
		  return null;
	  }
	  int mid = (idxL + idxR) / 2;
	  TreeNode left = helper(list, idxL, mid-1);
	  TreeNode root = new TreeNode(list.get(0).val);
	  root.left = left;
	  list.add(0, list.get(0).next);
	  root.right = helper(list, mid+1, idxR);
	  return root;
  }  
}