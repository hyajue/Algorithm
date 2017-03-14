/**
* Assume you have an array of length n initialized with all 0's and are given k update operations.
* 
* Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
* 
* Return the modified array after all k operations were executed.
* 
* Example:
* 
* Given:
* 
*     length = 5,
*     updates = [
*         [1,  3,  2],
*         [2,  4,  3],
*         [0,  2, -2]
*     ]
* 
* Output:
* 
*     [-2, 0, 3, 5, 3]
* Explanation:
* 
* Initial state:
* [ 0, 0, 0, 0, 0 ]
* 
* After applying operation [1, 3, 2]:
* [ 0, 2, 2, 2, 0 ]
* 
* After applying operation [2, 4, 3]:
* [ 0, 2, 5, 5, 3 ]
* 
* After applying operation [0, 2, -2]:
* [-2, 0, 3, 5, 3 ]
*/

/*
跟算法无关，这是个数学题
思想是把所有需要相加的值存在第一个数，然后把这个范围的最后一位的下一位(end+1)减去这个inc, 
这样我所以这个范围在求最终值的时候，都可以加上这个inc，而后面的数(end位之后的位)就不会加上inc
*/

public class RangeAddition {
  public int[] getModifiedArray(int length, int[][] updates) {
    if (length < 0) return null;
	int[] res = new int[length];
	for (int i = 0; i < updates.length; i++) {
	  int start = updates[i][0];
	  int end = updates[i][1];
	  int inc = updates[i][2];
	  res[start] += inc;
	  if (end < length - 1) {
		res[end+1] -= inc;
	  }
	}
	int sum = 0;
	for (int i = 0; i < length; i++){
	  sum += res[i];
	  res[i] = sum;
	}
	return res;
  } 
}

