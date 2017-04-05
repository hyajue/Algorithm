/**
* You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
* 
* You may assume the two numbers do not contain any leading zero, except the number 0 itself.
* 
* Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
* Output: 7 -> 0 -> 8
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
时间:O(n) 空间O(n)

思路：双指针 线性遍历
维护一个进位和一个当前位 两个指针分别指向链表1和链表2的头 每次加完和之后生成一个新的节点并把当前位赋值给新节点
链表1和2如果有剩余的就直接接在新链表后面
*/
 
public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		
		ListNode sumNode = new ListNode(0);
		ListNode ptrl1 = l1, ptrl2 = l2, ptrSumNode = sumNode;
		int carry = 0;
		int sum = 0;
		
		while (ptrl1 != null || ptrl2 != null || carry != 0) {
			if (ptrl1 != null) {
				sum += ptrl1.val;
				ptrl1 = ptrl1.next;
			}
			if (ptrl2 != null) {
				sum += ptrl2.val;
				ptrl2 = ptrl2.next;
			}
			sum += carry; //每次加上一位过来的进位
			int curDigit = sum % 10;
			ptrSumNode.next = new ListNode(curDigit);
			ptrSumNode = ptrSumNode.next;
			carry = sum / 10;
			sum = 0;
		}
		return sumNode.next;
	}
} 
