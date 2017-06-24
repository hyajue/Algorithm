/**
* Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
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
时间 O(NlogK) 空间 O(K)

思路:优先队列
当我们归并k个列表时，最简单的方法就是，对于每次插入，我们遍历这K个列表的最前面的元素，找出K个中最小的再加入到结果中。
不过如果我们用一个优先队列（堆），将这K个元素加入再找堆顶元素，每次插入只要logK的复杂度。
当拿出堆顶元素后，我们再将它所在链表的下一个元素拿出来，放到堆中。这样直到所有链表都被拿完，归并也就完成了。

因为堆中是链表节点，我们在初始化堆时还要新建一个Comparator的类。
*/

public class MergeKSortedLists {
  public ListNode mergeKLists(ListNode[] lists) {
    if(lists.length == 0) return null;
    ListNode dummy = new ListNode(0);
    PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			public int compare(ListNode node1, ListNode node2) {
				return node1.val - node2.val;
			}
		});
    // initialize size of queue as K	
    for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				queue.offer(lists[i]);
			}
		}
		ListNode curr = dummy;
		while(!queue.isEmpty()) {
			// get the first val from heap
			curr.next = queue.poll();
			curr = curr.next;
			// put the item next to the first one into heap 
			if (curr.next != null) {
				queue.offer(curr.next);
			}
		}
		return dummy.next;
  } 
}

/*
another solution: divide and conquer
*/

public static ListNode mergeKLists(ListNode[] lists){
  return partion(lists,0,lists.length-1);
}

public static ListNode partion(ListNode[] lists,int s,int e){
  if(s==e)  return lists[s];
  if(s<e){
    int q = (s+e)/2;
    ListNode l1=partion(lists,s,q);
    ListNode l2=partion(lists,q+1,e);
    return merge(l1,l2);
  } else
    return null;
}

//This function is from Merge Two Sorted Lists.
public static ListNode merge(ListNode l1,ListNode l2){
  if(l1==null) return l2;
  if(l2==null) return l1;
  if(l1.val<l2.val){
    l1.next=merge(l1.next,l2);
    return l1;
  } else{
    l2.next=merge(l1,l2.next);
    return l2;
  }
}





 