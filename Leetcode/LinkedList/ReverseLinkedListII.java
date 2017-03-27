/**
* Reverse a linked list from position m to n. Do it in-place and in one-pass.
* 
* For example:
* Given 1->2->3->4->5->NULL, m = 2 and n = 4,
* 
* return 1->4->3->2->5->NULL.
* 
* Note:
* Given m, n satisfy the following condition:
* 1 ≤ m ≤ n ≤ length of list.
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
时间 O(n) 空间O(n)

思路： 双指针迭代+dummy head 
先移动到翻转起点的前一个节点 把几个关键节点记录下来 然后利用翻转链表I中的办法 
把sublist[m,n]翻转过来 然后调整关键节点指针 

有关链表的问题 搞一个dummy head比较方便 不需要考虑一些corner cases
*/
 
public class ReverseLinkedListII {
  public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null) return head;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
		head = dummy;
		// move head to the node before target starting point
		// move m-1 steps
		for (int i = 1; i < m; i++) {
			head = head.next;
		}
		ListNode subHead = head.next;
		ListNode prevSubHead = head;
		ListNode ptr1 = head.next;
		ListNode ptr2 = head.next.next;
		for (int i = m; i < n; i++) {
		  ListNode p2Next = ptr2.next;
			ptr2.next = ptr1;
			ptr1 = ptr2;
			ptr2 = p2Next;
		}
		subHead.next = ptr2;
		prevSubHead.next = ptr1;
		return dummy.next;
  }
} 