/**
* Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.
* 
* Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.
* 
* Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.
* 
* Examples:
* 
* Input: "WRRBBW", "RB"
* Output: -1
* Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
* 
* Input: "WWRRBBWW", "WRBRW"
* Output: 2
* Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
* 
* Input:"G", "GGGGG"
* Output: 2
* Explanation: G -> G[G] -> GG[G] -> empty 
* 
* Input: "RBYYBBRRB", "YRBGB"
* Output: 3
* Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 
* 
* Note:
* You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
* The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
* The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
* Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
*/


public class ZumaGame {
  public int findMinStep(String board, String hand) {
    int res = -1;
    Map<Character, Integer> map = new HashMap<Character, Integer>();
		List<Character> list = new ArrayList<Character>();
    
		for (int i = 0; i < hand.length(); i++) {
			if (!map.containsKey(hand.charAt(i))) {
				map.put(hand.charAt(i), 1);
			} else {
				map.put(hand.charAt(i), map.get(hand.charAt(i))+1);
			}
		} 
    for (int i = 0; i < board.length(); i++) {
			list.add(board.charAt(i));
		}
		
		res = helper(list, map);
    return res;		
  }

  private int helper(List<Character> list, Map<Character, Integer> map) {
		removeTriple(list);
		if (list.size() == 0) return 0;
		if (isEmptyHand(map)) return -1;
		int cnt = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < list.size(); i++) {
			char cur = list.get(i);
			cnt++;
			if (i == list.size()-1 || list.get(i+1) != cur) {
				int toGet = 3 - cnt;
				if (map.containsKey(cur) && map.get(cur) >= toGet) {
					map.put(cur, map.get(cur) - toGet);
					List<Character> newList = new ArrayList<Character>(list);
					for (int j = 0; j < cnt; j++) {
						newList.remove(i-j);
					}
					int subRes = helper(newList, map);
					if (subRes != -1) {
						min = Math.min(subRes+toGet, min);
					}
					map.put(cur, map.get(cur)+toGet);
				}
				cnt = 0;
			}
		}
		return (min == Integer.MAX_VALUE) ? -1 : min;
	}	
	
	private void removeTriple(List<Character> list) {
		int cnt = 0;
		boolean removed = false;
		for (int i = 0; i < list.size(); i++) {
			char cur = list.get(i);
			cnt++;
			if (i == list.size()-1 || list.get(i+1) != cur) {
				if (cnt >= 3) {
					for (int j = 0; j < cnt; j++) {
						list.remove(i-j);
					}
					removed = true;
					break;
				}
				cnt = 0;
			}
		}
		if (removed) {
			removeTriple(list);
		}
	}
	
	private boolean isEmptyHand(Map<Character, Integer> map) {
		for (int val : map.values()) {
			if (val > 0) return false;
		}
		return true;
	}
}