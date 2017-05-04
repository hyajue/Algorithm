/**
* A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
* 
* Return a deep copy of the list.
*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

/*
solution 1
复杂度
时间：O(n) 空间O(1)

思路：双指针+三次扫描
fist pass: copy each node and connect copy next to original 
second pass: copy random field to copied node: cur.next.random = cur.random.next
third pass: get a deep copy from mixed list 
*/ 
 
public class CopyListWithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
	  if (head == null) return head;
    RandomListNode cur = head;
    // pass 1: copy each node and connect newNode to next of original one 
		while (cur != null) {
		  RandomListNode newNode = new RandomListNode(cur.label);
      newNode.next = cur.next;
      cur.next = newNode;
      cur = newNode.next;
		}
    // pass 2: copy random field to newNode  
    cur = head;
    while (cur != null && cur.next != null) {
		  if (cur.random != null) {
				// note that cur.random is original, cur.random.next is copy. 
				// so we point copy's random to random's copy 
				cur.next.random = cur.random.next;
			}	
			cur = cur.next.next;
		}
		// pass 3: get a deep copy from mixed list 
		RandomListNode newHead = head.next;
		cur = head;
		while (cur != null) {
			RandomListNode newNode = cur.next;
			cur.next = newNode.next;
			if (newNode.next != null) {
				newNode.next = newNode.next.next;
			}
			cur = cur.next;
		}
		return newHead;
	}
}

/*
solution 2
复杂度
时间：O(n) 空间：O(n) 

思路：HaspMap
maintain a HashMap: <key, value> = <original, copy>
first pass: generate HashMap
second pass: copy random field
*/ 

public class CopyListWithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) return head;
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode newHead = new RandomListNode(head.label);
		map.put(head, newHead);
		RandomListNode prev = newHead;
		RandomListNode cur = head.next;
		
		while (cur != null) {
			RandomListNode newNode = new RandomListNode(cur.label);
			map.put(cur, newNode);
			prev.next = newNode;
			prev = newNode;
			cur = cur.next;
		}
		
		cur = head;
		RandomListNode copiedCur = newHead;
		while (cur != null) {
			copiedCur.random = map.get(cur.random);
			copiedCur = copiedCur.next;
			cur = cur.next;
		}
		return newHead;
	}
}