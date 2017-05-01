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
复杂度
时间:O(n) 空间O:(1)

思路：双指针
快指针先跑k步 然后快慢指针一起同频跑 
当快指针指向原链表最后一个元素时，慢指针的下一点即为新的表头，慢指针现在指向元素为表尾
需要注意的点是旋转的结点数可能超过链表长度，所以我们要对这个进行取余
定位旋转的尾结点需要走过的距离不超过链表的长度->时间复杂度是O(n)，空间复杂度是O(1)
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
