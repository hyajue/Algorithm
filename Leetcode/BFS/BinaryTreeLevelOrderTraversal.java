/**
* Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
* 
* For example:
* Given binary tree [3,9,20,null,null,15,7],
*     3
*    / \
*   9  20
*     /  \
*    15   7
* return its level order traversal as:
* [
*   [3],
*   [9,20],
*   [15,7]
* ]
*/

/*
复杂度
时间:O(n) 空间：O(n)

思路：BFS
*/

public class BinaryTreeLevelOrderTraversal {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (root == null) return result;
    List<TreeNode> level = new ArrayList<TreeNode>();
    level.add(root);
    
    while (!level.isEmpty()) {
      List<Integer> values = new ArrayList<Integer>();
      for(int i = 0; i < level.size(); i++){
        values.add(level.get(i).val);
      }
      result.add(values);
      List<TreeNode> nextlevel = new ArrayList<TreeNode>();
      for (int j = 0 ;j < level.size(); j++){
          if (level.get(j).left != null) {
					  nextlevel.add(level.get(j).left);
					}
					
          if (level.get(j).right != null) {
					  nextlevel.add(level.get(j).right);
					}
      }
      level = nextlevel;
    }
    return result;
  }
}