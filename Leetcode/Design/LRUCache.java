/**
* Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
* 
* get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
* put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
* 
* Follow up:
* Could you do both operations in O(1) time complexity?
* 
* Example:
* 
* LRUCache cache = new LRUCache( 2 /* capacity */ );
* 
* cache.put(1, 1);
* cache.put(2, 2);
* cache.get(1);       // returns 1
* cache.put(3, 3);    // evicts key 2
* cache.get(2);       // returns -1 (not found)
* cache.put(4, 4);    // evicts key 1
* cache.get(1);       // returns -1 (not found)
* cache.get(3);       // returns 3
* cache.get(4);       // returns 4
*/

/*
复杂度
时间 O(1) for both get and put 空间O(n)

思路: map + doubly linked list
get(key) -- O(1) 很明显用一个hashmap来实现O(1)的查询操作
哈希表无序->无法做到least recent updated item deletion->考虑用队列
队列无法做到移动非首位的元素->当移动任意元素时,需要考虑前驱和后续的连接->考虑用双向链表
*/

public class LRUCache {
  
	int size;
	int capacity;
	Map<Integer, ListNode> map;
	ListNode tail;
	ListNode head;
	
  public LRUCache(int capacity) {
		this.head = new ListNode(0, 0);
    this.tail = new ListNode(0, 0);
		head.prev = tail;
		tail.next = head;
		this.size = 0;
		this.capacity = capacity;
		this.map = new HashMap<Integer, ListNode>();
  }
  
  public int get(int key) {
    ListNode cur = map.get(key);
		if (cur != null) {
			promote(cur);
			return cur.val;
		} else {
			return -1;
		}
  }
  
  public void put(int key, int value) {
    ListNode cur = map.get(key);
		if (cur == null) {
			cur = new ListNode(key, value);
			putToHead(cur);
			size++;
			//如果加入节点后超出容量 则删除最后一个节点(最不常用节点)
			if (size > capacity) {
				removeLast();
				size--;
			}
		} else {
			cur.val = value;
			promote(cur);
		}
		map.put(key, cur); //不论是否是新加入还是更新原链表 都要更新map
  }
	
	private void promote(ListNode cur) {
		cur.prev.next = cur.next;
		cur.next.prev = cur.prev;
		putToHead(cur);
	}
	
	private void putToHead(ListNode cur) {
		cur.next = head;
		cur.prev = head.prev;
		head.prev.next = cur;
		head.prev = cur;
	}
	
	private void removeLast() {
	  ListNode last = tail.next;
		last.next.prev = tail;
		tail.next = last.next;
		map.remove(last.key); //不仅要删除链表中的数据 也要删除相应的map的数据
	}
	
	private class ListNode {
		int val;
		int key;
		ListNode prev;
		ListNode next;
		
		public ListNode (int key, int val) {
			this.val = val;
			this.key = key;
			this.prev = null;
			this.next = null;
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */ 