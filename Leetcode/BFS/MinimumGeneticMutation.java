/**
* A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
* 
* Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.
* 
* For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
* 
* Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.
* 
* Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
* 
* Note:
* 
* Starting point is assumed to be valid, so it might not be included in the bank.
* If multiple mutations are needed, all mutations during in the sequence must be valid.
* You may assume start and end string is not the same.
* Example 1:
* 
* start: "AACCGGTT"
* end:   "AACCGGTA"
* bank: ["AACCGGTA"]
* 
* return: 1
* Example 2:
* 
* start: "AACCGGTT"
* end:   "AAACGGTA"
* bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
* 
* return: 2
* Example 3:
* 
* start: "AAAAACCC"
* end:   "AACCCCCC"
* bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
* 
* return: 3
*/

/*
复杂度
时间：O(4^n) 空间：O(n)

思路：bfs
跟word ladder一样思路
*/

public class MinimumGeneticMutation {
  public int minMutation(String start, String end, String[] bank) {
    if (start == null || end == null || start.length() != end.length()) return -1;
    int steps = 0;
    char[] mutations = new char[]{'A', 'C', 'G', 'T'};
    // transfer array to Set in order to get O(1) search time in bfs
		HashSet<String> validGene = new HashSet<String>();
    for (String str : bank) {
      validGene.add(str);
    }
		// end sequence must exsit in Set if valid
    if (!validGene.contains(end)) return -1;
    // start point can be removed at first since it's valid anyway
		if (validGene.contains(start)) validGene.remove(start);
    Queue<String> q = new LinkedList<String>();
    q.offer(start);
    while (!q.isEmpty()) {
      int size = q.size();
      for (int k = 0; k < size; k++) {
        String cur = q.poll();
        for (int i = 0; i < cur.length(); i++) {
          for (char c : mutations) {
            StringBuilder sb = new StringBuilder(cur);
            sb.setCharAt(i, c);
            String afterMutation = sb.toString();
            if (afterMutation.equals(end)) return steps+1;
            // remove after mutation sequence and enqueue if valid
						if (validGene.contains(afterMutation)) {
              validGene.remove(afterMutation);
              q.offer(afterMutation);
            }
          }
        }
      }
      steps++;
    }
    return -1;
  }
}