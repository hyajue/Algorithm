/**
* Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
* 
* For example,
* Given 1->2->3->3->4->4->5, return 1->2->5.
* Given 1->1->1->2->3, return 2->3.
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
时间O(n) 空间O(1)

思路：双指针
双指针操作，最后返回dummy.next，以防止head结点即duplicate的情况返回错误的结果。
令pre = dummy, cur = head，用cur进行查重操作，pre是cur的前结点。
当cur和cur.next等值的时候，cur后移至第一个不等值的点，用pre指向新的cur即可。
*/ 

public class RemoveDuplicatesFromSortedListII {
  public ListNode deleteDuplicates(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    ListNode cur = head;
    while (cur != null && cur.next != null) {
	    if (cur.val == cur.next.val) {
		  int val = cur.val;
		  while (cur != null && cur.val == val) {
		    cur = cur.next;
		  }
		  pre.next = cur;
	    } else {
		  cur = cur.next;
		  pre = pre.next;
	    }
	  }
    return dummy.next;    
  }
}
 