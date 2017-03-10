/**
* Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
* 
* You should preserve the original relative order of the nodes in each of the two partitions.
* 
* For example,
* Given 1->4->3->2->5->2 and x = 3,
* return 1->2->2->4->3->5.
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
使用链表常用的双指针算法，一个指向当前小于x的最后一个元素，一个进行往前扫描。如果元素大于x，那么继续前进，
否则，要把元素移到前面，并更新第一个指针。如果不需要移动（也就是已经是接在小于x的最后元素的后面了），那么只需要继续前进即可。
算法时间复杂度是O(n)，空间只需要几个辅助变量，是O(1)
*/
 
public class PartitionList {
  public ListNode partition(ListNode head, int x) {
    if (head == null) return null;
    ListNode dummy = new ListNode(0); 
	dummy.next = head; // introduces a dummy head
	ListNode faster = dummy; // both faster and slower point to dummy at beginning
	ListNode slower = dummy;
	while (faster.next != null) {
	  if (faster.next.val < x) {
	    if (faster != slower) {
		  ListNode next = faster.next.next;
		  faster.next.next = slower.next;
		  slower.next = faster.next;
		  faster.next = next;
		} else {
		    faster = faster.next;	
		}
		slower = slower.next;
	  } else {
		faster = faster.next;  
	  }
	}
	return dummy.next;
  }
}