/**
* Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
* 
* For example, 
* Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/

/*
solution 1:
复杂度
时间:O(n) 空间O(1)

思路：双指针
蓄水多少取决于比较短的那块板的长度 所以每次当左指针指向的板比较短的时候，就将其设置为一个limitor,每次向右移动，观察是否有比左边这个limitor小的板子的存在，如果有，说明到这个位置可以蓄水
*/
 
public class TrappingRainWater {
	public int trap(int[] height) {
	  if (height == null || height.length < 3) return 0;
		int maxVol = 0;
		int left = 0; 
		int right = height.length - 1;
		int limitor = 0;
		while (left < right) {
		  if (height[left] < height[right]) {
				limitor = height[left];
				int i = left + 1;
				while (i < right && height[i] < limitor) {
				  maxVol += limitor - height[i];
					i++;
				}
				left = i;
			} else {
			  limitor = height[right];
				int i = right - 1;
				while (i > left && height[i] < limitor) {
				  maxVol += limitor - height[i];
					i--;
				}
				right = i;
			}
		}
		return maxVol;
	}
}