/**
* Follow up for problem "Populating Next Right Pointers in Each Node".
* 
* What if the given tree could be any binary tree? Would your previous solution still work?
* 
* Note:
* 
* You may only use constant extra space.
* For example,
* Given the following binary tree,
*          1
*        /  \
*       2    3
*      / \    \
*     4   5    7
* After calling your function, the tree should look like:
*          1 -> NULL
*        /  \
*       2 -> 3 -> NULL
*      / \    \
*     4-> 5 -> 7 -> NULL
*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

/*
复杂度
时间：O(n) 空间：O(1)

思路：BFS 
由于条件变成任一二叉树 所以采用层序遍历法解决
*/

public class PopulatingNextRightPointersInEachNodeII {
  public void connect(TreeLinkNode root) {
    if(root == null) return;
    //记录该层当前节点的指针，也叫做父节点，我们通过遍历父节点，来连接它们的子节点
    TreeLinkNode p = root;
    //记录下层第一个节点的指针
    TreeLinkNode first = null;
    while(p != null){
      //当first为空时，说明刚跳转到新的一层，需要设置下一层的第一个节点了
      if(first == null){
          first = p.left;
      }
      //如果有左子节点，则其next是右子节点，如果没有，则遍历结束
      //因为我们实际上是将下一层的节点用next指针连接，所以当遍历到叶子结点时已经没有下一层
      if(p.left != null){
          p.left.next = p.right; 
      } else {
          break;
      }
      //如果父节点有next，则next的左子节点是父节点的右子节点的next，如果没有，说明这层遍历完了，转入下一层
      if(p.next != null){
          p.right.next = p.next.left;
          p = p.next;
      } else {
          p = first;
          first = null;
      }
    }
  }
}