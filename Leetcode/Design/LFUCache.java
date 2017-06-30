/**
* Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
* 
* get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
* put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
* 
* Follow up:
* Could you do both operations in O(1) time complexity?
* 
* Example:
* 
* LFUCache cache = new LFUCache( 2 /* capacity */ );
* 
* cache.put(1, 1);
* cache.put(2, 2);
* cache.get(1);       // returns 1
* cache.put(3, 3);    // evicts key 2
* cache.get(2);       // returns -1 (not found)
* cache.get(3);       // returns 3.
* cache.put(4, 4);    // evicts key 1.
* cache.get(1);       // returns -1 (not found)
* cache.get(3);       // returns 3
* cache.get(4);       // returns 4
*/

/*
复杂度
时间：O(1) 空间O(n)

思路
维护两个HashMap,一个存<Key, value>,用来get.一个存<Key,Node>,用来put
定义一个数据结构freqNode,存同一个使用频率的所有key.内部用LinkedHashSet存储
key的内容
*/

public class LFUCache {
  private int capacity;
  private freqNode head;
  Map<Integer, Integer> valMap;
  Map<Integer, freqNode> freqMap;
  
  public LFUCache(int capacity) {
    this.capacity = capacity;
    this.head = null;
    this.valMap = new HashMap<Integer, Integer>();
    this.freqMap = new HashMap<Integer, freqNode>();			
  }
  
  public int get(int key) {
    if (valMap.containsKey(key)) {
  		incCnt(key);
  		return valMap.get(key);
  	}
    return -1;			
  }
  
  public void put(int key, int value) {
    if (capacity == 0) return;
    if (valMap.containsKey(key)) {
  		valMap.put(key, value);
  		incCnt(key);
  	} else {
  		if (valMap.size() < capacity) {
  		  valMap.put(key, value);
  		  addToHead(key);
  		} else {
  		  removeOld();
  		  valMap.put(key, value);
  		  addToHead(key);
  	  }
  	}			
  }
  
  private void incCnt(int key) {
  	freqNode node = freqMap.get(key);
  	node.keys.remove(key);
  	if (node.next == null) {
  		node.next = new freqNode(node.count+1);
  		node.next.prev = node;
  		node.keys.add(key);
  	} else if (node.next.count == node.count+1) {
  		node.next.keys.add(key);
  	} else {
  		freqNode newNode = new freqNode(node.count+1);
  		newNode.next = node.next;
  		node.next.prev = newNode;
  		newNode.prev = node;
  		node.next = newNode;
  		node.next.keys.add(key);
  	}
  	freqMap.put(key, node.next);
  	if (node.keys.size() == 0) {
  		remove(node);
  	}
  }
  
  private void remove(freqNode node) {
  	if (node.next != null) {
  		node.next.prev = node.prev;
  	}
  	if (node.prev != null) {
  		node.prev.next = node.next;
  	} 
  	else { // node is head
  		head = head.next;
  	}
  }
  
  private void addToHead(int key) {
  	if (head == null) {
  		head = new freqNode(1);
  		head.keys.add(key);
  	} else if (head.count == 1) {
  		head.keys.add(key);
  	} else {
  		freqNode newHead = new freqNode(1);
  		head.prev = newHead;
  		newHead.next = head;
  		head = newHead;
  		head.keys.add(key);
  	}
  	freqMap.put(key, head);
  }
  
  private void removeOld() {
  	if (head == null) return;
  	int old = 0;
  	for (int key : head.keys) {
  		old = key;
  		break;
  	}
  	head.keys.remove(old);
  	if (head.keys.size() == 0) {
  		remove(head);
  	}
  	valMap.remove(old);
  	freqMap.remove(old);
  } 
  
  private class freqNode {
  	int count; 
  	freqNode prev;
  	freqNode next;
  	LinkedHashSet<Integer> keys;
  	
  	public freqNode(int freq) {
  	  this.count = freq;
  		this.keys = new LinkedHashSet<Integer>();
  		this.prev = null;
  		this.next = null;
  	}
  }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
 
 
 
 public class LFUCache {
    int cap;
    ListNode head;
    HashMap<Integer, Integer> valueMap;
    HashMap<Integer, ListNode> nodeMap;

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.head = null;
        this.valueMap = new HashMap<Integer, Integer>();
        this.nodeMap = new HashMap<Integer, ListNode>();
    }
    
    public int get(int key) {
        if (valueMap.containsKey(key)) {
            increaseCount(key);
            return valueMap.get(key);
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if (cap == 0) return;
        if (valueMap.containsKey(key)) {
            valueMap.put(key, value);
            increaseCount(key);
        }
        else {
            if (valueMap.size() < cap) {
                valueMap.put(key, value);
                addToHead(key);
            }
            else {
                removeOld();
                valueMap.put(key, value);
                addToHead(key);
            }
        }

    }
    
    public void increaseCount(int key) {
        ListNode node = nodeMap.get(key);
        node.keys.remove(key);
        if (node.next == null) {
            node.next = new ListNode(node.count+1);
            node.next.prev = node;
            node.next.keys.add(key);
        }
        else if (node.next.count == node.count + 1) {
            node.next.keys.add(key);
        }
        else {
            ListNode newNode = new ListNode(node.count+1);
            newNode.next = node.next;
            node.next.prev = newNode;
            newNode.prev = node;
            node.next = newNode;
            node.next.keys.add(key);
        }
        nodeMap.put(key, node.next);
        if (node.keys.size() == 0) remove(node);
    }
    
    public void remove(ListNode node) {
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        else { // node is head
            head = head.next;
        }
    }
    
    public void addToHead(int key) {
        if (head == null) {
            head = new ListNode(1);
            head.keys.add(key);
        }
        else if (head.count == 1) {
            head.keys.add(key);
        }
        else {
            ListNode newHead = new ListNode(1);
            head.prev = newHead;
            newHead.next = head;
            head = newHead;
            head.keys.add(key);
        }
        nodeMap.put(key, head);
    }
    
    public void removeOld() {
        if (head == null) return;
        int old = 0;
        for (int keyInorder : head.keys) {
            old = keyInorder;
            break;
        }
        head.keys.remove(old);
        if (head.keys.size() == 0) remove(head);
        valueMap.remove(old);
        nodeMap.remove(old);
    }
    
    public class ListNode {
        int count;
        ListNode prev, next;
        LinkedHashSet<Integer> keys;
        public ListNode(int freq) {
            count = freq;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */