/**
* Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
* 
* 
* OJ's undirected graph serialization:
* Nodes are labeled uniquely.
* 
* We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
* As an example, consider the serialized graph {0,1,2#1,2#2,2}.
* 
* The graph has a total of three nodes, and therefore contains three parts as separated by #.
* 
* First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
* Second node is labeled as 1. Connect node 1 to node 2.
* Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
* Visually, the graph looks like the following:
* 
*        1
*       / \
*      /   \
*     0 --- 2
*          / \
*          \_/
*/
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
 
/*
复杂度
时间：O(n) 空间：O(n)

思路：栈
利用栈模拟递归过程
*/
 
public class CloneGraph {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		
		Stack<UndirectedGraphNode> stack = new Stack<UndirectedGraphNode>();
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
		map.put(node, copy);
		stack.push(node);
		while (!stack.isEmpty()) {
			UndirectedGraphNode curNode = stack.pop();
			for (int index = 0; index < curNode.neighbors.size(); index++) {
				if (!map.containsKey(curNode.neighbors.get(index))) {
					copy = new UndirectedGraphNode(curNode.neighbors.get(index).label);
					map.put(curNode.neighbors.get(index), copy);
					stack.push(curNode.neighbors.get(index));
				}
				map.get(curNode).neighbors.add(map.get(curNode.neighbors.get(index)));
			}
		}
		return map.get(node);
	}		
}

/*
复杂度
时间：O(n) 空间：O(n)

思路：bfs
维护一个HashMap,里面的键值对为：<原始节点,复制节点>,每次遍历到一个新的节点,都生成一个复制节点,并把原始节点和复制节点
插入哈希表中.这样也同时记录了访问过的节点:只要哈希表中存在的节点即为访问过的节点
遍历时,维护一个队列,先将输入节点入队,然后遍历输入节点的邻居列表,只要遇到哈希表中不存在的节点,就复制,然后入队,不管是否之前已经生成过复制节点,
都需要讲该节点的复制品放到当前节点复制节点的邻居列表里
*/
public class CloneGraph {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) return null;
    Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    UndirectedGraphNode root = new UndirectedGraphNode(node.label);
    map.put(node, root);
    queue.offer(node);
    while (!queue.isEmpty()) {
      UndirectedGraphNode cur = queue.poll();
      // 将cur旧节点的邻居节点都加入cur的新节点
      for (UndirectedGraphNode oldNeighbor : cur.neighbors) {
        // 判断是否已经生成过该邻居节点的新节点
        if (!map.containsKey(oldNeighbor)) {
          // 如果是第一次生成该新节点，将其加入队列中
          map.put(oldNeighbor, new UndirectedGraphNode(oldNeighbor.label));
          queue.offer(oldNeighbor);
        }
				// 将新邻居加入新cur节点的neighbors中
        map.get(cur).neighbors.add(map.get(oldNeighbor));
      }
    }
    return root;
  }
}