/**
* Implement an iterator to flatten a 2d vector.
* For example,
* Given 2d vector =
* [
*   [1,2],
*   [3],
*   [4,5,6]
* ]
* By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
* Hint:
* How many variables do you need to keep track?
* Two variables is all you need. Try with x and y.
* Beware of empty rows. It could be the first few rows.
* To write correct code, think about the invariant to maintain. What is it?
* The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
* Not sure? Think about how you would implement hasNext(). Which is more complex?
* Common logic in two different places should be refactored into a common method.
*/

/*
复杂度
时间：O(n) 空间：O(1)

思路
维护两个坐标x,y表示当前遍历到的元素.调用hasNext()验证x,y是否合法并且跳过空行
调用next()返回当前元素,并且更新x,y
*/

public class Vector2D {
  private int x;
  private int y;
  private List<List<Integer>> list;

  public Vector2D(List<List<Integer>> vec2d) {
		if (vec2d == null) {
			return;
		}
		this.x = 0;
		this.y = 0;
		this.list = vec2d;
	}  
	
	public int next() {
		int res = list.get(x).get(y);
		if (y+1 >= list.get(x).size()) {
			y = 0;
			x += 1;
		} else {
			y += 1;
		}
		return res;
	}
	
	public boolean hasNext() {
		if (list == null) return false;
		while (x < list.size() && list.get(x).size() == 0) {
			x += 1;
			y = 0;
		}
		if (x >= list.size()) return false;
		if (y >= list.get(x).size()) return false;
		return true;
	}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */ 