/**
* There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
* 
* You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
* 
* Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
* 
* Note:
* The solution is guaranteed to be unique.
*/
 
/*
复杂度
时间 O(n) 空间O(1)

思路：贪心
引入单次剩余油量res，剩余油量和sum，总剩余油量和total，以及可行起点start四个参数
只要total > 0 就有解存在 
当sum < 0的时候，一定是上一个加油站的单次剩余油量res为负，且与上一次的剩余油量和sum相加依然为负，说明在上一个加油站出现了消耗大于补给的情况，因此一定不能将它作为起点
所以跳过这个耗油量很大的油站，然后将下一个油站作为起点继续判断即可
*/

public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start = 0;
		int sum = 0;
		int total = 0;
		for (int i = 0; i < gas.length; i++) {
			int residual = gas[i] - cost[i];
			if (sum < 0) {
				start = i;
				sum = residual;
			} else {
				sum += residual;
			}
			total += residual;
		}
		return total >= 0 ? start : -1;
	}
}