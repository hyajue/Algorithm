/**
* The set [1,2,3,…,n] contains a total of n! unique permutations.
* 
* By listing and labeling all of the permutations in order,
* We get the following sequence (ie, for n = 3):
* 
* "123"
* "132"
* "213"
* "231"
* "312"
* "321"
* Given n and k, return the kth permutation sequence.
* 
* Note: Given n will be between 1 and 9 inclusive.
*/

/*
时间 O(N) 空间 O(1)

由于我们只要得到第K个全排列，而不是所有全排列，我们不一定要将所有可能都搜索一遍。根据全排列顺序的性质，
我们可以总结出一个规律：假设全排列有n个数组成，则第k个全排列的第一位是k/(n-1)!. 为了更形象一点，举例如下：

123
132
213
231
312
321

在这种情况下，第一个数字每2!=2个情况就改变一次，假设求第6个排列，我们先将其减1，方便整除运算，然后5/2=2。
对于第一位，我们有三种可选数字1、2、3，所以5/2=2意味着我们选择第3个数字，也就是3（如果商是s，则选第s+1个数字）
然后将5%2得到1，这个1就是下一轮的k。

这里有一个技巧，就是用一个列表将1到n存起来，每选用一个数就是移出那个数，就能保证不选重复数字的同时，其顺序也是一样的。
*/

public class PermutationSequence {
  public String getPermutation(int n, int k) {
    List<Integer> nums = new ArrayList<Integer>();
    int mod = 1;
    // get n! and cadidate number list
    for (int i = 1; i <= n; i++) {
	    mod = mod * i;
	    nums.add(i);
	  }
    //decrement k to make division convenient  
    k--;
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < n; i++) {
			mod = mod / (n - i);
			
			int first = k / mod;
			k %= mod;
			sb.append(nums.get(first));
			nums.remove(first);
	  }
    return sb.toString(); 	
  }
} 
