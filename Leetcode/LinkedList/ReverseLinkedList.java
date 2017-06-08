/**
* Reverse a singly linked list.
* 
* Hint:
* A linked list can be reversed either iteratively or recursively. Could you implement both?
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
solution 1 
复杂度
时间 O(n) 空间O(n) 

思路：递归 
递归函数每次返回一个节点 该返回节点为当前调用节点的前一个节点
边界条件：
当调用节点本身或下一个节点为空时 即为原链表的末尾 将新头结点指向改节点即可

注意
递归法在LC上无法AC 总是TLE 
*/ 
 

public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    List<NodeList> list = new ArrayList<NodeList>();
		helper(list, head);
    return list.get(0);    
  }
	private ListNode helper(List<NodeList> list, ListNode n) {
		if (n == null || n.next == null) {
			list.set(0,n);
		} else {
		  ListNode prev = helper(n.next); // 注意这里传入递归函数的是n.next 画个图更好理解
			prev.next = n; //返回的prev是n节点在原顺序下的后续节点 
		}
		return n; //无论if还是else 都要返回该层的n给上一层
	}
} 

/*
solution 2 
复杂度
时间 O(n) 空间O(1) 

思路：双指针迭代
每次调整指针1指针2的指向关系 一直到指针2指向null 指针1即为原链表尾节点->新表头
注意最后要将原头节点head的next指向null
*/ 
 
public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode ptr1 = head;
		ListNode ptr2 = ptr1.next;
		while (ptr2 != null) {
			ListNode p2Next = ptr2.next;
			ptr2.next = ptr1;
			ptr1 = ptr2;
			ptr2 = p2Next;
		}
		head.next = null;
		return ptr1;
	}
} 