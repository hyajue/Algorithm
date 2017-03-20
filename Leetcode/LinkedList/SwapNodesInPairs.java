/**
* Given a linked list, swap every two adjacent nodes and return its head.
* 
* For example,
* Given 1->2->3->4, you should return the list as 2->1->4->3.
* 
* Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
时间 O(N) 空间 O(1)

思路:三指针
指针为p，我们选择swap的两个结点是p.next和p.next.next。要注意while循环的边界条件，这两个结点不能为空。
主要思路是先用next和temp两个新结点去保存p.next.next.next和p.next两个结点。完成交换之后，连接temp和next，并让p前进至此时的temp结点。
*/ 
 
public class SwapNodesInPairs {
  public ListNode swapPairs(ListNode head) {
    if(head == null) {
	  return head;
	}  
	ListNode dummy = new ListNode(0);
	dummy.next = head;
	ListNode curr = dummy;
	while(curr.next != null && curr.next.next != null) {
	  ListNode next = curr.next.next.next;
	  ListNode prev = curr.next;
	  curr.next = curr.next.next;
	  curr.next.next = prev;
	  prev.next = next;
	  curr = prev;
	}
	return dummy.next;
  }
} 