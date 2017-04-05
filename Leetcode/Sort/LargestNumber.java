/**
* Given a list of non negative integers, arrange them such that they form the largest number.
* 
* For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
* 
* Note: The result may be very large, so you need to return a string instead of an integer.
*/

/*
复杂度
时间O(n) 空间O(n)

思路： 
先把输入数组排序 自定义Comparator：按照最高位的大小进行排序 
*/


public class LargestNumber {
  public String largestNumber(int[] nums) {
    if (nums == null || nums.length == 0) return "";
		Integer[] copy = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++) {
			copy[i] = nums[i];
		}
		//自定义Comparator 按照拼接后的字符串大小比较
		Arrays.sort(copy, new Comparator<Integer>() {
			public int compare(Integer item1, Integer item2) {
				String str1 = String.valueOf(item1);
				String str2 = String.valueOf(item2);
				String combo1 = str1 + str2;
				String combo2 = str2 + str1;
				return combo2.compareTo(combo1);
			}	
		});
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 && copy[i] == 0) {
				return "0";
			}
			sb.append(copy[i]);
		}
		return sb.toString();
  }
} 