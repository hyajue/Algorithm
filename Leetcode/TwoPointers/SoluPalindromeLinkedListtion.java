/**
* Given a singly linked list, determine if it is a palindrome.
* 
* Follow up:
* Could you do it in O(n) time and O(1) space?
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
使用快慢指针找中点的原理是fast和slow两个指针，每次快指针走两步，慢指针走一步，等快指针走完时，慢指针的位置就是中点
找到中点后，将后半段的链表翻转一下，这样我们就可以按照回文的顺序比较了
*/

public class SoluPalindromeLinkedListtion {
  public boolean isPalindrome(ListNode head) {
    if(head == null) return true;
    ListNode middle = findMiddle(head);    
    middle.next = reverse(middle.next);
    ListNode ptr1 = head;
	ListNode ptr2 = middle.next;
	while(ptr1 != null && ptr2 != null) {
		if (ptr1.val != ptr2.val) return false;
		ptr1 = ptr1.next;
		ptr2 = ptr2.next;
	}
    return true;	
  }
  private ListNode findMiddle(ListNode head) {
	if (head == null) return null;
    ListNode slow = head;
	ListNode fast = head;
	while (fast.next != null) {
	  if (fast.next.next == null) break;
	  slow = slow.next;
	  fast = fast.next.next;
	}
	return slow;
  }
  private ListNode reverse(ListNode head) {
	ListNode prev = null;
	while(head != null) {
      ListNode tmp = head.next; 
	  head.next = prev;
	  prev = head;
	  head = tmp;
	}
	return prev;
  }
} 