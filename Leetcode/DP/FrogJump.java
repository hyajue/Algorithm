/**
* A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
* 
* Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
* 
* If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
* 
* Note:
* 
* The number of stones is ≥ 2 and is < 1,100.
* Each stone's position will be a non-negative integer < 231.
* The first stone's position is always 0.
* Example 1:
* 
* [0,1,3,5,6,8,12,17]
* 
* There are a total of 8 stones.
* The first stone at the 0th unit, second stone at the 1st unit,
* third stone at the 3rd unit, and so on...
* The last stone at the 17th unit.
* 
* Return true. The frog can jump to the last stone by jumping 
* 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
* 2 units to the 4th stone, then 3 units to the 6th stone, 
* 4 units to the 7th stone, and 5 units to the 8th stone.
* Example 2:
* 
* [0,1,2,3,4,8,9,11]
* 
* Return false. There is no way to jump to the last stone as 
* the gap between the 5th and 6th stone is too large.
*/


public class FrogJump {
	public boolean canCross(int[] stones) {
	  if (stones == null || stones.length == 0) return false;
		if (stones[1] != 1) return false; //第一次只能跳一步
		int len = stones.length;
		int[][] canJump = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				canJump[i][j] = -1;
			}
		}
		return jumpable(canJump, stones, 0, 0, len);
	}
	
	private boolean jumpable(int[][] canJump, int[] stones, int i, int j, int length) {
		if (canJump[i][j] != -1) return canJump[i][j] == 1;
		if (i == length - 1) {
			canJump[i][j] = 1;
			return true;
		}
		for (int k = i + 1; k < length; k++) {
			// 距离太近 
			if (stones[k] < stones[i] + j - 1) {
				continue;
			//距离太远	
			} else if (stones[k] > stones[i] + j + 1) {
				canJump[i][j] = 0;
				continue;
			} else {
				//递归检查
				if (jumpable(canJump, stones, k, stones[k] - stones[i], length)) {
					canJump[i][j] = 1;
					return true;
				}
			}
		}
		canJump[i][j] = 0;
		return false;
	}
} 










