/**
* Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
* 
* Note:
* 1. If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
* 2. All airports are represented by three capital letters (IATA code).
* 3. You may assume all tickets form at least one valid itinerary.
* 
* Example 1:
* tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
* Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
* 
* Example 2:
* tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
* Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
* Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
*/

/*
复杂度
时间：O(n+e) 空间：O(n)

思路：dfs+优先队列
将机票的起点和重点抽象成图的顶点,用邻接表的形式建立图
对于多个合法的行程,需要返回最小的lexical order,所以对某个起点,建立一个优先队列，把各个重点放入队列中.
有了图之后,就可以利用dfs来遍历整个图
*/

public class ReconstructItinerary {
  public List<String> findItinerary(String[][] tickets) {
    List<String> res = new ArrayList<String>();
    if (tickets == null || tickets.length == 0) return res;
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    // construct the graph using adj list
    for (String[] ticket : tickets) {
			if (!map.containsKey(ticket[0])) {
				PriorityQueue<String> pq = new PriorityQueue<String>();
				map.put(ticket[0], pq);
			}
			map.get(ticket[0]).offer(ticket[1]);
		}
    dfs("JFK", map, res);
    return res;		
  }
	
	private void dfs(String from, Map<String, PriorityQueue<String>> map, List<String> res) {
		PriorityQueue<String> pq = map.get(from);
		while (pq != null && !pq.isEmpty()) {
			dfs(pq.poll(), map, res);
		} 
		res.add(0, from);
	}
}