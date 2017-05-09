/**
* Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
* 
* For example,
* Given n = 3, there are a total of 5 unique BST's.
* 
*    1         3     3      2      1
*     \       /     /      / \      \
*      3     2     1      1   3      2
*     /     /       \                 \
*    2     1         2                 3
*/

/*
复杂度
时间O(n^2) 空间O(n)

思路：dp
首先想二叉查找树的性质：中序遍历为有序数组即可构成一个二叉查找树 根随便取
所以任选一个值为根，然后把剩下的集合分成了左右子树->构建子问题
以该选取节点作为根的二叉树的数量=左子树可能的数量*右子树可能的数量
因此，维护一个数组res，res[i]表示有i个节点的二叉查找树的数量 
对于有n个节点的BST,左边有i个节点,右边就有n-1-i个节点
*/
 
public class UniqueBinarySearchTrees {
	public int numTrees(int n) {
		if (n <= 0) return 0;
		int[] res = new int[n+1];
		res[0] = 1;
		res[1] = 1;
		for (int i = 2; i <= n; i++) {
			//left subtree has j nodes: j->[0,i)
			for (int j = 0; j < i; j++) {
				res[i] += res[j] * res[i-j-1];
			}
		}
		return res[n];
	}
} 