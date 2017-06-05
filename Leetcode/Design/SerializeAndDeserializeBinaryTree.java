/**
* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
* 
* Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
* 
* For example, you may serialize the following tree
* 
*     1
*    / \
*   2   3
*      / \
*     4   5
* as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
* Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/

/*
复杂度
时间:O(n) 空间：O(n)

思路：先序遍历
对输入二叉树进行序列化,实质上是对其进行一次遍历.在这里选择先序遍历,因为这样反序列化时每次出来的第一个数就是根,容易定位根.
维护一个队列,存放序列化的结果,反序列化时,也按照先序遍历的顺序,出队一个元素,新建节点,然后递归构建左/右节点即可.

follow up： 如何实现N-ary tree的序列/反序列化？
思路类似,先按一个顺序序列化,然后在按照相同顺序反序列化.
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
public class Codec {
  
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) return "null";
    StringBuilder sb = new StringBuilder();
    sb.append(root.val);
    String left = serialize(root.left);
    String right = serialize(root.right);
    sb.append(",");
    sb.append(left);
    sb.append(",");
    sb.append(right);
    return sb.toString();
  }
  
  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    Queue<String> queue = new LinkedList<String>();
    String[] nodes = data.split(",");
		for (String node : nodes) {
			queue.offer(node);
		}
		return treeConstructor(queue);
  }
	
	private TreeNode treeConstructor(Queue<String> queue) {
		if (queue == null || queue.size() == 0) return null;
		String cur = queue.poll();
		if (cur.equals("null")) return null;
		TreeNode root = new TreeNode(Integer.parseInt(cur));
		root.left = treeConstructor(queue);
		root.right = treeConstructor(queue);
		return root;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));