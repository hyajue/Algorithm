/**
* Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
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

思路:顺序插入
该题就是简单的把两个链表的节点拼接起来，我们可以用一个Dummy头，将比较过后的节点接在这个Dummy头之后。
最后如果有没比较完的，说明另一个list的值全比这个list剩下的小，而且拼完了，所以可以把剩下的直接全部接上去。
*/

public class MergeTwoSortedLists {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // create a dummy node as head 
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    while(l1 != null && l2 != null) {
	  if (l1.val <= l2.val) {
	    curr.next = l1;
		l1 = l1.next;
	  } else {
		curr.next = l2;
		l2 = l2.next;
	  }
	  curr = curr.next;
	}
    // connect the rest of the nodes 
    if (l1 != null) {
	  curr.next = l1;
	} else if (l2 != null) {
	  curr.next = l2;
	}
    return dummy.next;	
  }
}
