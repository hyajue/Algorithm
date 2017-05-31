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

/*
复杂度
时间：O(n*m*log(m+n)) 空间：O(m+n)
思路：bfs+优先队列
1、构造一个优先队列，每次都取出高度最矮的一个。 
2、首先将四周的桩都加入，因为记得四周是无法盛水的 
3、每次从优先队列中取出一个最矮的cell，若他比未访问过的四周的四个高了delta h，那么总的盛水量多加delta h，否则不加，注意四周只要一访问过下次就不能访问了。
因为若当前cell被取出，且其某个邻居cell-n高度低于cell，那么可以得出：cell-n的邻居里面最矮的就是cell，所以cell可以决定cell-n的盛水量。
*/

public class TrappingRainWaterII {
  public int trapRainWater(int[][] heightMap) {
    if (heightMap == null || heightMap.length == 0) return 0;
		
		// helper class Cell: defines each of the grid 
		class Cell {
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
		
		PriorityQueue<Cell> pq = new PriorityQueue<Cell>(new Comparator<Cell>() {
		  public int compare(Cell cell1, Cell cell2) {
				return cell1.h - cell2.h;
			}
		});
    boolean[][] visited = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			visited[i][0] = true;
			visited[i][col-1] = true;
			pq.offer(new Cell(i, 0, heightMap[i][0]));
			pq.offer(new Cell(i, col-1, heightMap[i][col-1]));
		}
		for (int j = 0; j < col; j++) {
			visited[0][j] = true;
			visited[row-1][j] = true;
			pq.offer(new Cell(0, j, heightMap[0][j]));
			pq.offer(new Cell(row-1, j, heightMap[row-1][j]));
		}
    int[] dy = {0, 0, -1, 1};
		int[] dx = {-1, 1, 0, 0};
		int sum = 0;
		int maxHeight = Integer.MIN_VALUE;
		while (!pq.isEmpty()) {
			Cell cell = pq.poll();
			maxHeight = Math.max(cell.h, maxHeight);
			for (int i = 0; i < 4; i++) {
				int curX = cell.x + dx[i];
				int curY = cell.y + dy[i];
				if (curX >= 0 && curY >= 0 && curX < row && curY < col && !visited[curX][curY]) {
					visited[curX][curY] = true;
					if (heightMap[curX][curY] < maxHeight) {
						sum += maxHeight - heightMap[curX][curY];
					}
					pq.offer(new Cell(curX, curY, heightMap[curX][curY]));
				}
			}
		} 
    return sum;
  }
}

/*
注意点：
为什么 if (heightMap[x][y] < mx) res += mx - heightMap[x][y];

这一行的条件是访问到了某一个没visit的块高度为 heightMap[x][y]，并且此时的全局水位mx比之高，结果是此块能积水 mx - heightMap[x][y]

1. 为什么能积水，即当前这一块不会通过比自己更矮的块流出去：因为遍历开始讲边缘的块都放入了优先队列，因此如果对于当前块，
存在一条通向边缘的路径使得路径上的点高度都<当前块高度，那一定在水位上升到比它高之前被遍历到，与条件冲突

2. 为什么积水不会 < mx - heightMap[x][y]: 即为什么不存在一个高度 h<mx, h>heightMap[x][y]，使得在 x,y处 >h 的水会流出。
如果存在，那这个h所在块将存在一条到边缘的路径使得路径上的所有块高度都<h（否则无法流出）。
根据优先队列以及BFS，h一定可以在水位上升到>h的高度之前被访问，使得访问到h的时候水位也为h。同样，也存在一条从h所在块到当前块的路径使得路径上所有块高度都<h且>heightMap[x][y]，使得水能够从h块流出。
那么在水位为h的时候，当前块就会被访问到，当水位上升至mx>h时，当前块已经访问了，与条件冲突

3. 为什么积水不会 > mx - heightMap[x][y]，即设当前水位mx=h1为围绕在当前块周围的高度，为什么不会在当前mx对应边缘的外层存在一层更高h2的边缘，使的在当前块上能积水h2>h1=mx：如果存在这种情况，
那么访问到h2边缘层的时候，mx已经上升到h2的高度，也就是访问到当前块时，mx=h2>h1，与条件冲突
*/