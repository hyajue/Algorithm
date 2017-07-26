/**
* You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
* 
* You may assume the two numbers do not contain any leading zero, except the number 0 itself.
* 
* Follow up:
* What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
* 
* Example:
* 
* Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
* Output: 7 -> 8 -> 0 -> 7
* 
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class AddTwoNumbersII {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    while (l1 != null) {
      stack1.push(l1.val);
      l1 = l1.next;			
		}		
		
		while (l2 != null) {
			stack2.push(l2.val);
			l2 = l2.next;
		}
		
		ListNode dummy = new ListNode(0);
		int carry = 0;
		
		while (!stack1.isEmpty() && !stack2.isEmpty()) {
		  int val1 = 0;
      int val2 = 0;
      if (!stack1.isEmpty()) val1 = stack1.pop();
      if (!stack2.isEmpty()) val2 = stack2.pop();
      
			ListNode cur = new ListNode();
			cur.val = (va11 + val2 + carry) % 10;
			carry = (va11 + val2 + carry) / 10;
			dummy.next = cur;
      			
		}
  }
} 











