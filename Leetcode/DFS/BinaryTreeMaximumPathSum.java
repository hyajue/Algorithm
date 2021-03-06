/**
* Given a binary tree, find the maximum path sum.
* 
* For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
* 
* For example:
* Given the below binary tree,
* 
*        1
*       / \
*      2   3
* Return 6.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
/*
solution 1:
复杂度
时间：O(n) 空间：O(logn)

思路：
这道题是求树的路径和的题目，不过和平常不同的是这里的路径不仅可以从根到某一个结点，而且路径可以从左子树某一个结点，然后到达右子树的结点，
就像题目中所说的可以起始和终结于任何结点。在这里树没有被看成有向图，而是被当成无向图来寻找路径。因为这个路径的灵活性，我们需要对递归返回值进行一些调整，
而不是通常的返回要求的结果。在这里，函数的返回值定义为以自己为根的一条从根到子结点的最长路径（这里路径就不是当成无向图了，必须往单方向走）。
这个返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可。
这样一来，一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。而返回值则是自己的值加上左子树返回值，
右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）。在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新。
算法的本质还是一次树的遍历，所以复杂度是O(n)。而空间上仍然是栈大小O(logn)
这道题是比较有难度 要用返回值去维护一个中间量 而结果值则通过参数来维护
*/
 
public class BinaryTreeMaximumPathSum {
  public int maxPathSum(TreeNode root) {
    if (root == null) {
  	  return 0;
    }
    List<Integer> res = new ArrayList<Integer>();
    res.add(Integer.MIN_VALUE);
    helper(root, res);
    return res.get(0);
  }
	
	private int helper(TreeNode root, List<Integer> res) {
		if (root == null) {
			return 0;
		}
		int left = helper(root.left, res);
		int right = helper(root.right, res);
		int cur = root.val + (left > 0 ? left:0) + (right > 0 ? right:0);
		if (cur > res.get(0)) {
			res.set(0, cur);
		}
		return root.val + Math.max(left, Math.max(right, 0));
	}
}

/*
solution 2:
当前这个路径和，是一系列任意节点的和，只要这个路径上的相邻两个节点有parent-child连接就可以了。

我们要明白的一点是: 路径和一定是以某个节点node 为中间连接的路径。
又可以具体扩展：以节点node为中间连接的路径 =  当前的node.val + max(0, maxPath(node.left)) + max(0, maxPath(node.right))
加max是为了处理这类情况：

       1 
      / \
     -2  -3
maxPath函数表示当前节点能够组成的最大 path sum，注意，不一定要到leaf node的。
*/

public class BinaryTreeMaximumPathSum {
  private int maxSum = Integer.MIN_VALUE;
	
	public int maxPathSum(TreeNode root) {
    if (root == null) {
		  return 0;
	  }
	  return helper(root); 
  }
	
	private int helper(TreeNode root) {
    if (root == null) {
			return 0;
		} 
		int left = helper(root.left);
		int right = helper(root.right);
		int curSum = root.val + Math.max(0, left) + Math.max(0, right);
		maxSum = Math.max(curSum, maxSum);
		return Math.max(Math.max(left, right) + root.val, root.val);
	}
}

/*
solution 3:
For each node like following, there should be four ways existing for max path:
1. Node only （因为本题中的节点可能是负值！）
2. L-sub + Node
3. R-sub + Node
4. L-sub + Node + R-sub

Keep trace the four path and pick up the max one in the end.  
明确递归函数的返回值是什么：这本题中返回值表示通过root节点能走到root的parent的最大和，这个值作为返回对象返给调用父函数
因为在Java中无法像C++一样引用传值或者利用指针传值, 所以用数组传值
*/

public class BinaryTreeMaximumPathSum {
	public int maxPathSum(TreeNode root) {
        if (root == null) {
			return 0;
		}
		int[] max = {Integer.MIN_VALUE}; 
		helper(root, max);
		return max[0];
    }
	private int helper(TreeNode root, int[] max) {
        if (root == null) {
			return 0;
		} 
		
		int left = helper(root.left, max); // maxSum from left subtree
		int right = helper(root.right, max); // maxSum from right subtree
		int arch = left + right + root.val; // maxSum from left through this root to to right
		
		// max value through this root node to its parent node, this value will be 
		// returned to caller 
		// note that arch path won't pass parent since it already passed root. Back routing 
		// is not allowed. 
		int maxThisToParent = Math.max(root.val, Math.max(left, right)+root.val);
		 
		max[0] = Math.max(max[0], Math.max(arch, maxThisToParent));
		return maxThisToParent;
	}
}