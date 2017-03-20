/**
* Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
* 
* k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
* 
* You may not alter the values in the nodes, only nodes itself may be changed.
* 
* Only constant memory is allowed.
* 
* For example,
* Given this linked list: 1->2->3->4->5
* 
* For k = 2, you should return: 2->1->4->3->5
* 
* For k = 3, you should return: 3->2->1->4->5
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

思路:递归分段翻转
每K个节点执行一次反转链表，有几点要注意：

如果剩余节点不足K个则不执行反转
利用递归思想每次翻转含有K个节点的子链表
K等于0或1的时候直接返回原链表
*/ 
 
public class ReverseNodesInKGroup {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null || k == 0 || k == 1) return head;
    ListNode start = head; 
    ListNode end = head;
    int cnt = 0;
    while (cnt < k && end.next != null) {
	  end = end.next;
	  cnt++;
	}
    if(cnt == k) {
	  ListNode next = end.next;
	  reverse(start, end);
	  start.next = reverseKGroup(next, k);
	  return end;
	}
	return start;  
  }
  private void reverse(ListNode start, ListNode end) {
    ListNode pre = null;
	while (start != end) {
	  ListNode next = start.next;
	  start.next = pre;
	  pre = start;
	  start = next;
	}
	start.next = pre;
  }
} 

















