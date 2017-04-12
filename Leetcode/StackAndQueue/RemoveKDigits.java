/**
* Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
* 
* Note:
* The length of num is less than 10002 and will be ≥ k.
* The given num does not contain any leading zero.
* Example 1:
* 
* Input: num = "1432219", k = 3
* Output: "1219"
* Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
* Example 2:
* 
* Input: num = "10200", k = 1
* Output: "200"
* Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
* Example 3:
* 
* Input: num = "10", k = 2
* Output: "0"
* Explanation: Remove all the digits from the number and it is left with nothing which is 0.
*/

/*
复杂度
时间 O(n^2) 空间O(n)

思路：
看例子，首先是1432219，k = 3，不去掉1的原因是后面接的是4，当前这一步，看到下一个数比自己大的时候移掉是不划算的，因为移掉这个数之后最高位变成4，
是不如保留1小的。所以可以看出规律实际上是从msb开始只要发现比之前有比当前位大的数字，那肯定要移掉之前的数字，这样当前最高位的数字就变小了。
后面的3和2需要移掉也是同理。用个Stack来保存之前递增的数字。

首先通过用字符与'0'相减的结果int cur进行数值大小的比较。然后遍历整个字符串，将较小的元素替换栈内较大元素并放在栈底，形成一个从底部到顶端逐渐增大的堆栈。
例如0812743456（不考虑删除元素个数k），堆栈的排列从下到上就变成123456了。又例如087123654，k = 2,那么放入堆栈后就变成0123654。
有两种可能情况：删掉的元素数目小于或等于k。
如果在for循环中正好删除了k个元素，这k个元素一定是从原字符串A的高位（栈底）开始删除的。所以无论删除k个元素之后的元素放入顺序如何，此时栈内元素从底到顶的排列一定是满足条件的最小值。
如果在for循环删除的元素少于k个，一定是这样的情况：081234567, k = 3, 在for循环结束的时候只删除了元素8，因为剩下的元素是一个完全上升序列01234567。这种情况下，就要从堆栈顶部删除剩下的两个元素6和7. 
然后，将栈内的元素放入StringBuilder，并将StringBuilder顶部的'0'全部去掉，然后以.toString()返回String
*/
 
public class RemoveKDigits {
	public String removeKdigits(String num, int k) {
	  if (num == null || k < 0) return null;
		Stack<Integer> stack = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		int delNum = 0;
		for (int i = 0; i < num.length(); i++) {
		  int cur = num.charAt(i) - '0';
			while (!stack.isEmpty() && stack.peek() > cur && delNum < k) {
				stack.pop();
				delNum++;
			}
			stack.push(cur);
		}
		while(delNum < k && !stack.isEmpty()) {
		  stack.pop();
			delNum++;
		}
		if (stack.isEmpty()) {
		  sb.insert(0, "0");
		}
		while (!stack.isEmpty()) {
		  sb.insert(0, stack.pop());
		}
		while (sb.charAt(0) == '0' && sb.length() > 1) {
		  sb.deleteCharAt(0);
		}
		return sb.toString();
	}
}