/**
* Given a 2D board and a word, find if the word exists in the grid.
* 
* The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
* 
* For example,
* Given board =
* 
* [
*   ['A','B','C','E'],
*   ['S','F','C','S'],
*   ['A','D','E','E']
* ]
* word = "ABCCED", -> returns true,
* word = "SEE", -> returns true,
* word = "ABCB", -> returns false.
*/

/*
复杂度
时间 O(m^2*n^2) 空间(n*m)

思路： 深搜
从某一个元素出发，往上下左右深度搜索是否有相等于word的字符串
注意每次从一个元素出发时要重置访问标记（虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符可以复用）

follow up: what if char can be used mutiple times? 
*/

public class WordSearch {
  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0) return false;
    if (word == null || word.length() == 0) return true;
    boolean[][] visited = new boolean[board.length][board[0].length];
	for (int i = 0; i < board.length; i++) {
	  for (int j = 0; j < board[0].length; j++) {
		if(dfs(board, word, visited, 0, i, j)) {
		  return true;
		}
	  }  
	}
    return false;	
  }
  private boolean dfs(char[][] board, String word, boolean[][] visited, int len, int x, int y) {
	if (len == word.length()) {
	  return true;
	}
	if (x<0 || y<0 || x>=board.length || y>=board[0].length || visited[x][y] || board[x][y]!=word.charAt(len)) {
	  return false;
	}
	visited[x][y] = true;
	boolean res = dfs(board, word, visited, len+1, x-1, y)
				|| dfs(board, word, visited, len+1, x+1, y)
				|| dfs(board, word, visited, len+1, x, y+1)
				|| dfs(board, word, visited, len+1, x, y-1);
	visited[x][y] = false;
	return res;
  }
}












 