/**
* Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
* 
* For example:
* 
* Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
* 
* Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
* 
* Hint:
* 
* Given n = 5 andedges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
* According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected byexactly one path. In other words, any connected graph without simple cycles is a tree.”
* Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

/*
复杂度
时间：O(n^2) 空间：O(n)

思路：Union-find 
判断一个图是不是一棵树 1.首先应该有n-1条边　2.边没有形成环
*/

public class GraphValidTree {  
  public boolean validTree(int n, int[][] edges) {  
    if (edges.length != n-1) return false; // n-1 edges should hold
	  int[] root = new int[n];
	  for (int i = 0; i < n; i++) {
	    root[i] = i; // 初始化节点数组 每个节点根初始化为自己	
	  }
	  for (int i = 0; i < edges.length; i++) {
	  	int root1 = find(root, edges[i][0]);
	  	int root2 = find(root, edges[i][1]);
	  	if (root1 == root2) return false; // find cycle
	  	root[root2] = root1; // Union: merge two edges 
	  }
	  return true;
  }
  
	private int find(int[] root, int e) {
	  if (root[e] == e) return e;
	  int rootIdx = find(root, root[e]);
	  return rootIdx;
  }
} 

/*
复杂度
时间：O(n + e) 空间：O(n)

思路：DFS 
*/

public class GraphValidTree {  
  public boolean validTree(int n, int[][] edges) {  
    if (edges.length != n-1) return false;
		Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		boolean[] visited = new boolean[n];
		boolean[] isLoop = new boolean[n];
		for (int[] edge : edges) {
			if (!graph.containsKey(edge[0])) {
				graph.put(edge[0], new ArrayList<Integer>());
			}
			if (!graph.containsKey(edge[1])) {
				graph.put(edge[1], new ArrayList<Integer>());
			}
      graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				if (!dfs(graph, visited, isLoop, i)) return false;
			}
		}
		return true;
	}
	
	private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] isLoop, int v) {
		if (visited[v]) return true;
		if (isLoop[v]) rturn false;
		isLoop[v] = true;
		for (int nabor : graph.get(v)) {
			if (!dfs(graph, visited, isLoop, nabor)) return false;
		}
		visited[v] = true;
		return true;
	}
}




