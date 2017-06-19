/**
* Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
* 
* Example 1:
* Input: [3, 2, 1]
* 
* Output: 1
* 
* Explanation: The third maximum is 1.
* Example 2:
* Input: [1, 2]
* 
* Output: 2
* 
* Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
* Example 3:
* Input: [2, 2, 3, 1]
* 
* Output: 1
* 
* Explanation: Note that the third maximum here means the third maximum distinct number.
* Both numbers with value 2 are both considered as second maximum.
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：treeSet
用TreeSet存放数组中的uninque元素，Treeset 可以自动排序（升序）
*/

public class ThirdMaximumNumber {
  public int thirdMax(int[] nums) {
    TreeSet<Integer> set = new TreeSet<Integer>();
    for (int num : nums) {
			set.add(num);
		}
		int[] res = new int[set.size()];
		int idx = 0;
		for (int num : set) {
		  res[idx] = num;
      idx++;			
		}
		if (set.size() < 3) return res[set.size()-1];
		return res[set.size()-3];
  }
} 

/*
复杂度
时间：O(n) 空间：O(1)

思路：
用三个整型变量来记录，由于题目没说有没有负数，所以没法定义一个绝对小于数组中所有数字的初始值，只能以数组中第一个数字来作为初始值，
然后遍历一个一个数字去比较看应该替代三个数字中哪个数字，注意如果比第一个数字大，那么原本第一个数字的值要移动到第二个，
原本第二个数字的值要移动到第三个，如果替代的是第二个数字，同样要把原本第二个数字的值移动到第三个，道理很简单。
由于用第一个数字作为初始值，因此在替换数字时还有一个原因就是如果第二个和第三个数字还是初始值，而出现了与初始值不同的数字，那就不要求比原数字大了，
直接替换并后移，否则如果第一个数字最大，那即使有第三大数字也不会记录下来。

由于题目说了是非空数组，所以不用考虑空数组特殊情况。

最后要判断三个数字是不是不一样，不一样才返回第三大数字，否则就要返回最大的数字。
*/

public class ThirdMaximumNumber {
  public int thirdMax(int[] nums) {
    int first = nums[0];
    int second = nums[0];
    int third = nums[0];
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > first) {
        third = second;
        second = first;
        first = nums[i];
      } else if (nums[i] != first && (nums[i] > second || second == first)) {
        third = second;
        second = nums[i];
      } else if ((nums[i] != first && nums[i] != second) && (nums[i] > third || third == second || third == first)) {
          third = nums[i];
      }
    }
    if (first > second && second > third) return third;
    else return first;
  }
}