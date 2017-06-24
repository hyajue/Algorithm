/**
* Given a binary tree
* 
*     struct TreeLinkNode {
*       TreeLinkNode *left;
*       TreeLinkNode *right;
*       TreeLinkNode *next;
*     }
* Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
* 
* Initially, all next pointers are set to NULL.
* 
* Note:
* 
* You may only use constant extra space.
* You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
* For example,
* Given the following perfect binary tree,
*          1
*        /  \
*       2    3
*      / \  / \
*     4  5  6  7
* After calling your function, the tree should look like:
*          1 -> NULL
*        /  \
*       2 -> 3 -> NULL
*      / \  / \
*     4->5->6->7 -> NULL
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
这道题是要将一棵树的每一层维护成一个链表，树本身是给定的
其实思路上很接近层序遍历Binary Tree Level Order Traversal，只是这里不需要额外维护一个队列
因为这里每一层我们会维护成一个链表，这个链表其实就是每层起始的那个队列，
因此我们只需要维护一个链表的起始指针就可以依次得到整个队列了 
算法的复杂度仍然是对每个结点访问一次，所以是O(n)，
而空间上因为不需要额外空间来存储队列了，所以是O(1)
这道题是树的层序遍历Binary Tree Level Order Traversal的扩展，操作上会更加繁琐一些，
因为是通过维护层链表来完成遍历，不过本质上还是一次广度优先搜索
*/
 
public class PopulatingNextRightPointersInEachNode {
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