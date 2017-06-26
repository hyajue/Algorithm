/**
* Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
* 
* You may assume each number in the sequence is unique.
* 
* Follow up:
* Could you do it using only constant space complexity?
*/

/*
复杂度
时间：O(n) 空间：O(n)

思路：stack
对于一个搜索二叉树的前序序列来说, 如果某段序列为一个递减序列, 说明这是一段沿着左子树的路径.
直到碰到一个比前一个大的值, 说明此时已经来到某个结点的右子树上了, 而此时可以得出一个此后序列的下界值, 
也就是此后序列的任意一个值必须要比这个结点的父结点的值大, 因为对于搜索二叉树来说根节点左边的都比根节点小, 
而根节点右边的都比根节点大, 所以既然现在已经来到某个结点(设为A)的右子树上, 那么此后任何结点的值必然比A的值大. 

那么当碰到一个比之前结点大的值如何找到他的父结点呢? 可以借助一个栈, 即如果当前结点比栈顶元素小, 
就入栈, 如果当前值大于栈顶值, 则让所有比当前结点小的值都出栈, 直到栈顶元素比当前结点大, 
则最后一个出栈的比当前结点小的值就是当前结点的父结点, 我们只要在栈元素出栈的时候更新最小下界再将当前元素入栈即可
*/

public class VerifyPreorderSequenceInBinarySearchTree {
  public boolean  verifyPreorder(int[] preorder) {
    Stack<Integer> stack = new Stack<Integer>();
    if (preorder == null || preorder.length == 0) return true;
    int low = Integer.MAX_VALUE;
    for (int num : preorder) {
      if (num < low) {
        return false;  
      }
      while (!stack.isEmpty() && num > stack.peek()) {
        low = stack.pop();  
      }
      stack.push(num);
    }
    return true;
  }
}

/*
空间优化
如何做到常数空间？
利用输入数组模拟栈.我们已经检查过的数字之后就不会再用到了,所以可以随意覆盖
*/

public class VerifyPreorderSequenceInBinarySearchTree {
  public boolean  verifyPreorder(int[] preorder) {
    if (preorder == null || preorder.length == 0) return true;
    int low = Integer.MAX_VALUE;
    int idx = -1;
    for (int num : preorder) {
      if (num < low) {
        return false;  
      }
      while (idx >= 0 && low > preorder[idx]) {
        low = preorder[idx--];  
      }
      preorder[++idx] = num;
    }
    return true;
  }
}

