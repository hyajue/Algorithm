/**
* Sort a linked list in O(n log n) time using constant space complexity.
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
时间O(nlogn) 空间O(1)

思路：二分分治
要求时间复杂度为O(nlogn)->想到用归并排序
1.先分 将链表分割成两个部分->快慢指针法
2.再治 递归滴对前半段和后半段排序 然后归并 
*/
 
public class SortList {
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
		ListNode mid = getMid(head); //找中点
		ListNode node2 = sortList(mid.next); //递归对后半段排序
		mid.next = null; //截断前半段链表
		ListNode node1 = sortList(head); //递归对前半段排序 
		return merge(node1, node2); //对分别排序好的前后半边链表排序
  }
	
	private ListNode getMid(ListNode head) {
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	private ListNode merge(ListNode node1, ListNode node2) {
		ListNode dummy = new ListNode(0);
		ListNode head = dummy;
		while (node1 != null && node2 != null) {
			if (node1.val < node2.val) {
				head.next = node1;
				node1 = node1.next;
			} else {
				head.next = node2;
				node2 = node2.next;
			}
			head = head.next;
		}
		if (node1 != null) {
			head.next = node1;			
		} else if (node2 != null) {
			head.next = node2;
		}
		return dummy.next;
	}
}