/**
* There are a total of n courses you have to take, labeled from 0 to n - 1.
* 
* Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
* 
* Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
* 
* There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
* 
* For example:
* 
* 2, [[1,0]]
* There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
* 
* 4, [[1,0],[2,0],[3,1],[3,2]]
* There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
* 
* Note:
* The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
* You may assume that there are no duplicate edges in the input prerequisites.
*/

/*
复杂度
时间：O(n+v) 空间：O(n)

思路：拓扑排序
1.建图
2.dfs,同时判断是否有环
3.reverse dfs postorder 
*/

public class CourseScheduleII {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] res = new int[numCourses];
    if (numCourses <= 0) return new int[]{};
    Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
    for (int[] edge : prerequisites) {
			if (!graph.containsKey(edge[1])) {
				graph.put(edge[1], new ArrayList<Integer>());
			}
			if (!graph.containsKey(edge[0])) {
				graph.put(edge[0], new ArrayList<Integer>());
			}
			graph.get(edge[1]).add(edge[0]);
		}
    Stack<Integer> stack = new Stack<Integer>();
    boolean[] isLoop = new boolean[numCourses];
    boolean[] visited = new boolean[numCourses];
    for (int course : graph.keySet()) {
			if (!dfs(graph, course, isLoop, visited, stack)) {
				return new int[]{};
			}
		}
		int idx = 0;
    while (!stack.isEmpty()) {
			res[idx++] = stack.pop();
		}
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				res[idx++] = i;
			}
		}
    return res;  		
  }
	
	private boolean dfs(Map<Integer, List<Integer>> graph, int course, boolean[] isLoop, boolean[] visited, Stack<Integer> stack) {
		if (visited[course]) return true;
		if (isLoop[course]) return false;
		isLoop[course] = true;
		for (int nabor : graph.get(course)) {
			if (!dfs(graph, nabor, isLoop, visited, stack)) {
				return false;
			}
		}
		visited[course] = true;
		stack.push(course);
		return true;
	}
}