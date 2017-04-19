/**
* Given a linked list, remove the nth node from the end of list and return its head.
* 
* For example,
* 
*    Given linked list: 1->2->3->4->5, and n = 2.
* 
*    After removing the second node from the end, the linked list becomes 1->2->3->5.
* Note:
* Given n will always be valid.
* Try to do this in one pass.
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
时间 O(n) 空间O(1)

思路：双指针 追击问题 
快指针先跑n步 慢指针再跑 当快指针跑到链表尾部的null 慢指针的next即指向待删除节点 
*/
 
public class RemoveNthNodeFromEndOfList {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) return head;
		int idx = 0;
		ListNode fast = head;
		while (fast != null && idx < n) {
			fast = fast.next;
			idx++;
		}
		if (idx < n) {
			return head;
		}
		if (fast == null) { // 恰好跑够N步
			return head.next;
		}
		ListNode slow = head;
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		slow.next = slow.next.next;
		return head;
  }
} 