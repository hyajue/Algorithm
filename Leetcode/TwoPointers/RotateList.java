/**
* Given a list, rotate the list to the right by k places, where k is non-negative.
* 
* For example:
* Given 1->2->3->4->5->NULL and k = 2,
* return 4->5->1->2->3->NULL.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
/*
利用快慢指针定位到要旋转的点 然后设置那一点的下一点为新的表头 当前点设置为表尾
需要注意的点就是旋转的结点数可能超过链表长度，所以我们要对这个进行取余
定位旋转的尾结点的不超过链表的长度，所以时间复杂度是O(n)，空间复杂度是O(1)
*/ 

public class RotateList {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) {
	  return null;	
	}
    ListNode faster = head;
	ListNode slower = head;
	int idx = 0;
	while (faster != null && idx < k) {
	  idx++;
	  faster = faster.next;
	}
	if (faster == null) {
	  k %= idx; // k exceeds the whole length of list. We take mod of the length
	  faster = head;
	  idx = 0;
	  while (faster != null && idx < k) {
	    faster = faster.next;
	  	idx++;
	  }
	}
	while (faster.next != null) {
		slower = slower.next;
		faster = faster.next;
	}
	faster.next = head;
	ListNode newHead = slower.next;
	slower.next = null;
	return newHead;
  }
}
























 


