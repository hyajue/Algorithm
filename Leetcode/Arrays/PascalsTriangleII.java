/**
* Given an index k, return the kth row of the Pascal's triangle.
* 
* For example, given k = 3,
* Return [1,3,3,1].
* 
* Note:
* Could you optimize your algorithm to use only O(k) extra space?
*/

/*
对于每一行我们知道如果从前往后扫，第i个元素的值等于上一行的res[i]+res[i+1]，可以看到数据是往前看的，
如果我们只用一行空间，那么需要的数据就会被覆盖掉 所以这里采取的方法是从后往前扫，这样每次需要的数据就是res[i]+res[i-1]，
我们需要的数据不会被覆盖，因为需要的res[i]只在当前步用，下一步就不需要了 
这个技巧在动态规划省空间时也经常使用，主要就是看我们需要的数据是原来的数据还是新的数据来决定我们遍历的方向 
时间复杂度还是O(n^2)，而空间这里是O(k)来存储结果，仍然不需要额外空间
*/


public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
		List<Integer> res = new ArrayList<Integer>();
		if (rowIndex < 0) {
			return res;
		}
		res.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			for (int j = res.size() - 2; j >= 0; j--) {
				res.set(j+1, res.get(j)+res.get(j+1));
			}
			res.add(1);
		}
		return res;
    }
}
 

