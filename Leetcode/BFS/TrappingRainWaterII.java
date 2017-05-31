/**
* the volume of water it is able to trap after raining.
* 
* Note:
* Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
* 
* Example:
* 
* Given the following 3x6 height map:
* [
*   [1,4,3,1,3,2],
*   [3,2,1,3,2,4],
*   [2,3,3,2,3,1]
* ]
* 
* Return 4.
*/


public class TrappingRainWaterII {
  public int trapRainWater(int[][] heightMap) {
    if (heightMap == null || heightMap.length == 0) return 0;
		
		// helper class Cell: defines each of the grid 
		private class Cell {
			private int x;
			private int y;
			private int h;
			
			public Cell(int x, int y, int h) {
			  this.x = x;
        this.y = y;
				this.h = h;
			}
		}
    
		int row = heightMap.length;
		int col = heightMap[0].length;
		
		Queue<Cell> queue = new
















    
  }
}
 