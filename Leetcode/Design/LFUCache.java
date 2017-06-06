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
时间：O(logN) 空间：O(n)

思路：map + priority queue
自定义数据结构Item,含属性key,value,frequency, and last used time,
利用Item方便优先队列排序
维护一个map,保存key—>Item,用来实现get.
维护一个优先队列,当两个Item使用频率不等时,按频率从小到大排序;若使用频率相等,则按照上次使用时间从小到大排序
每次需要选择哪个位置被覆盖时,直接从优先队列出队即可
*/

public class LFUCache {
  int size;
	int capacity;
	Map<Integer, Item> map;
	PriorityQueue<Item> pq;
 	
  public LFUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    this.map = new HashMap<Integer, Item>();
    this.pq = new PriorityQueue<Item>(new Comparator<Item>() {
		  public int compare(Item item1, Item item2) {
				if (item1.freq != item2.freq) {
					return item1.freq - item2.freq;
				} else {
					return item1.lastUsedTime - item2.lastUsedTime;
				}
			}
		});    
  }
  
  public int get(int key) {
    if (map.containsKey(key)) {
		  Item prev = map.get(key);
			pq.remove(prev);
			prev.freq++;
			prev.lastUsedTime++;
			pq.offer(prev);
			return map.get(key).val;	
		} else {
			return -1;
		}    
  }
  
  public void put(int key, int value) {
    if (!map.containsKey(key) && size < capacity) {
			Item cur = new Item(key, value, 0, 0);
			map.put(key, cur);
			pq.offer(cur);
			size++;
		} else if (!map.containsKey(key)){
			Item top = pq.poll();
			int keyTop = top.key;
			map.remove(keyTop);
			Item cur = new Item(key, value, 0, 0);
			map.put(key, cur);
			pq.offer(cur);
		}    
  }
	
	private class Item {
		private int key;
		private int val;
		private int freq;
		private int lastUsedTime;
		
		public Item(int key, int val, int freq, int lastUsedTime) {
			this.key = key;
			this.val = val;
			this.freq = freq;
			this.lastUsedTime = lastUsedTime;
		}
	}
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */ 
