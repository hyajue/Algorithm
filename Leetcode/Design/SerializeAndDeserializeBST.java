/**
* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
* 
* Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
* 
* The encoded string should be as compact as possible.
* 
* Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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
复杂度
时间：O(n) 空间：O(n)

思路：先序遍历+反先序遍历 using recursion
跟一般二叉树的序列化和反序列化思路一样.选择先序遍历,维护一个队列,反序列化时,先将各节点加入队列中,
然后在按照先序遍历的顺序构造BST
*/
 
public class Codec {
  
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    // use an indicator "$" as null.
		if (root == null) return "$";
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
    return BSTconstructor(queue);		
  }
	
	private TreeNode BSTconstructor(Queue<String> queue) {
		if (queue == null || queue.size() == 0) return null;
		String node = queue.poll();
		if (node.equals("$")) return null;
		TreeNode root = new TreeNode(Integer.parseInt(node));
		root.left = BSTconstructor(queue);
		root.right = BSTconstructor(queue);
		return root;
	}
}

/*
复杂度
时间：O(n) 空间：O(n)

思路：先序遍历+反先序遍历 using stack
*/

public class Codec {
  
  // Encodes a tree to a single string.  
  public String serialize(TreeNode root) {  
    StringBuffer sb = new StringBuffer("");  
    Stack<TreeNode> stack = new Stack<TreeNode>();  
    stack.push(root);  
    while(stack.size() != 0){  
      root = stack.pop();  
      if(root != null){  
        stack.push(root.right);  
        stack.push(root.left);  
        sb.append(root.val);  
        sb.append(",");  
      }  
    }  
    return sb.toString();  
  }  
  
  // Decodes your encoded data to tree.  
  public TreeNode deserialize(String data) {  
    if(data.length() == 0) return null;  
    String[] numbers = data.split(",");  
    TreeNode root = new TreeNode(Integer.parseInt(numbers[0]));  
    TreeNode iteratorNode = root;  
    Stack<TreeNode> stack = new Stack<TreeNode>();  
    for(int i = 1; i < numbers.length; i ++){  
      TreeNode node = new TreeNode(Integer.parseInt(numbers[i]));  
      if (node.val < iteratorNode.val) {
				iteratorNode.left = node;stack.push(iteratorNode);
			}  
      else {  
        while(!stack.empty()){  
          TreeNode parentNode = stack.pop();  
          if(node.val < parentNode.val) {
						iteratorNode.right = node;
						stack.push(parentNode);
						break;
					} else {
						iteratorNode = parentNode;
					}						
        }  
        if(stack.empty()) {
				  iteratorNode.right = node;
				}					       
      }  
      iteratorNode = node;  
    }  
      
    return root;  
  }  
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));