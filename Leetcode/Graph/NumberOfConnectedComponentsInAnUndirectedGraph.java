/**
* Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
* 
* Example 1:
* 
*     0          3
*     |          |
*     1 --- 2    4
* Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
* 
* Example 2:
* 
*     0           4
*     |           |
*     1 --- 2 --- 3
* Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
*/

/*
复杂度
时间：O(n+V) 空间：O(n)

思路：dfs
*/

public class NumberOfConnectedComponentsInAnUndirectedGraph {
  public int countComponents(int n, int[][] edges) {
    if (n <= 1) return n;
		int cnt = 0;
		boolean[] visited = new visited[n];
		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
		for (int[] edge : edges) {
			int cur = edge[0];
			int other = edge[1];
			if (!adjList.containsKey(cur)) {
				map.put(cur, new ArrayList<Integer>());
			}
			if (!adjList.containsKey(other)) {
				map.put(other, new ArrayList<Integer>());
			}
			map.get(cur).add(other);
			map.get(other).add(cur);
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(adjList, i, visited);
				cnt++;
			}
		}
		return cnt;
  }
	
	private void dfs(Map<Integer, List<Integer>> adjList, int v, boolean[] visited) {
	  visited[v] = true;
    for (int nabor : adjList.get(v)) {
			if (!visited[nabor]) {
				dfs(adjList, nabor, visited);
			}
		}		
	}
}