/**
* Design a data structure that supports all following operations in average O(1) time.
* 
* Note: Duplicate elements are allowed.
* insert(val): Inserts an item val to the collection.
* remove(val): Removes an item val from the collection if present.
* getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
* Example:
* 
* // Init an empty collection.
* RandomizedCollection collection = new RandomizedCollection();
* 
* // Inserts 1 to the collection. Returns true as the collection did not contain 1.
* collection.insert(1);
* 
* // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
* collection.insert(1);
* 
* // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
* collection.insert(2);
* 
* // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
* collection.getRandom();
* 
* // Removes 1 from the collection, returns true. Collection now contains [1,2].
* collection.remove(1);
* 
* // getRandom should return 1 and 2 both equally likely.
* collection.getRandom();
*/

/*
用list存储插入元素，用hashmap维护元素与下标之间的对应关系，不同点在于用一个集合存储某元素所有下标
（因为可能有重复元素插入，但它们是按序插入list的即下标是不同的），那么接下来考虑用什么集合存储某元素的所有下标

首先考虑list，但当我们remove某元素时，需要更新某元素的下标集合，此时用list，时间复杂度为O(n)

考虑set，因为是按序插入list的，即下标是不同的，hashset可以存储，且remove操作时间复杂度为O(1)
*/

public class RandomizedCollection {
  //用来索引下在ArrayList中的关系，做到快速定位
  private HashMap<Integer,Stack<Integer>> locations;
  //存储原始数据
  private ArrayList<Integer> list;
  private Random random;
  //为了排序，控制栈的
  ArrayList<Integer> tmpForStack;
  
  /** Initialize your data structure here. */
  public RandomizedCollection() {
    this.locations = new HashMap<Integer,Stack<Integer>>();
    this.list = new ArrayList<Integer>();
    this.random = new Random();
    this.tmpForStack = new ArrayList<Integer>();
  }
  
  
  /**
   * 直接插入
   * */
  public boolean insert(int val) {
    boolean flag = false;
    if( locations.containsKey(val) == false ){
      locations.put(val,new Stack<Integer>());
    }
    if(locations.get(val).isEmpty()) {
		  flag = true;	
		}
    locations.get(val).push(list.size());
    list.add(val);
    return flag;
  }
  
  /** Removes a value from the set. Returns true if the set contained the specified element. */
  /**
   * 核心思想在于：把删除的元素交换到ArrayList最后一位上，这样就可以O1完成了,
   * 
   * 唯一的一点不同是，要保证在每一个元素对应的位置的栈，要保证其顺序是从小到大，每次出栈的一定是最大的，所以这里交换List后，更新那个元素的栈时需要先把比他大的弹出来，再放回去
   * */
  public boolean remove(int val) {
    if( locations.containsKey(val) == false || locations.get(val).isEmpty() ) return false;
    int tmpLocation = locations.get(val).pop();
    if(tmpLocation != list.size() - 1){
      int lastVal = list.get(list.size() -1 );
      list.set(tmpLocation,lastVal);
      locations.get(lastVal).pop();
      tmpForStack.clear();
      while(locations.get(lastVal).isEmpty() == false && locations.get(lastVal).peek() > tmpLocation){
        tmpForStack.add(locations.get(lastVal).pop());
      }
      locations.get(lastVal).push(tmpLocation);
      for(int tval:tmpForStack)
        locations.get(lastVal).push(tval);
    }
    list.remove(list.size() - 1);
    return true;
  }
  
  /** Get a random element from the set. */
  public int getRandom() {
    return list.get(random.nextInt(list.size()));
  }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */