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
因此我们只需要维护一个链表的起始指针就可以依次得到整个队列了 接下来就是有左加左入链表，有右加右入链表，
知道链表没有元素了说明到达最底层了 算法的复杂度仍然是对每个结点访问一次，所以是O(n)，
而空间上因为不需要额外空间来存储队列了，所以是O(1)
这道题是树的层序遍历Binary Tree Level Order Traversal的扩展，操作上会更加繁琐一些，
因为是通过维护层链表来完成遍历，不过本质上还是一次广度优先搜索
*/
 
public class PopulatingNextRightPointersInEachNode {
    public void connect(TreeLinkNode root) {
        if (root == null) {
			return;
		}
		TreeLinkNode preHead = root;
		TreeLinkNode pre = null;
		TreeLinkNode curHead = null;
		while (preHead != null) {
			TreeLinkNode lastCur = preHead;
			while (lastCur != null) {
				if (lastCur.left != null) {
					if (curHead == null) {
						curHead = lastCur.left;
						pre = curHead;
					} else {
						pre.next = lastCur.left;
						pre = pre.next;
					}
				}
				if (lastCur.right != null) {
					if (curHead == null) {
						curHead = lastCur.right;
						pre = curHead;
					} else {
						pre.next = lastCur.right;
						pre = pre.next;
					}
				}
				lastCur = lastCur.next;
			}
			preHead = curHead;
			curHead = null;
		}
    }
}