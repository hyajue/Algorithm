/**
* Design a data structure that supports all following operations in average O(1) time.
* 
* insert(val): Inserts an item val to the set if not already present.
* remove(val): Removes an item val from the set if present.
* getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
* Example:
* 
* // Init an empty set.
* RandomizedSet randomSet = new RandomizedSet();
* 
* // Inserts 1 to the set. Returns true as 1 was inserted successfully.
* randomSet.insert(1);
* 
* // Returns false as 2 does not exist in the set.
* randomSet.remove(2);
* 
* // Inserts 2 to the set, returns true. Set now contains [1,2].
* randomSet.insert(2);
* 
* // getRandom should return either 1 or 2 randomly.
* randomSet.getRandom();
* 
* // Removes 1 from the set, returns true. Set now contains [2].
* randomSet.remove(1);
* 
* // 2 was already in the set, so return false.
* randomSet.insert(2);
* 
* // Since 2 is the only number in the set, getRandom always return 2.
* randomSet.getRandom();
*/

/*
O(1)的插入删除明显考虑用hash set或map，set存没法getRandom，则自然想到用hash map

因为要getRandom()，即key必须是连续的，当删除某元素时可能导致key不连续，所以当删除元素时要保证key空间连续，
简单的，我们让最后元素填补到被删除元素的位置，使得key空间连续。
*/


public class RandomizedSet {
	private List<Integer> val;
	private Map<Integer, Integer> valToIdx;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        val = new ArrayList<Integer>();
		valToIdx = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valToIdx.containsKey(val)) {
			return false;
		}
		else {
			valToIdx.put(val, valToIdx.size());
			val.add(val);
			return true;
		}
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!valToIdx.containsKey(val)) {
			return false;
		}
		else {
			// V =HashMap.remove(K)
			int idx = valToIdx.remove(val);
			if (idx < val.size() - 1) {    // if it's not the last item
				// put the last item on the index of removed item 
				val.set(idx, val.get(val.size()-1);
				valToIdx.put(val.get(val.size()-1), idx);
			}
			val.remove(val.size()-1);
			return true;
		}
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rdm = new Random();
		return val.get(rdm.nextInt(val.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */ 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
