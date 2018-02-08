package Airbnb;
import java.util.*;
/**
* Given: An array of strings where L indicates land and W indicates water,
*   and a coordinate marking a starting point in the middle of the ocean.
*
* Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os.
*   An ocean coordinate is defined to be the initial coordinate if a W, and
*   any coordinate directly adjacent to any other ocean coordinate.
*
* void findOcean(String[] map, int row, int column);
*
* String[] map = new String[]{
*  "WWWLLLW",
*  "WWLLLWW",
*  "WLLLLWW"
* };
* printMap(map);
*
* STDOUT:
* WWWLLLW
* WWLLLWW
* WLLLLWW
*
* findOcean(map, 0, 1);
*
* printMap(map);
*
* STDOUT:
* OOOLLLW
* OOLLLWW
* OLLLLWW
*/
public class floodFill {
	static int[] dx = new int[]{1,0,0,-1};
	static int[] dy = new int[]{0,1,-1,0};
	public static void fill(char[][] board,int i,int j){
		int m = board.length;
		int n = board[0].length;
		Queue<int[]> q = new LinkedList<>();
		board[i][j] = 'O';
		q.offer(new int[]{i,j});
		while(!q.isEmpty()){
			int[] cur = q.poll();
			int x0 = cur[0];
			int y0 = cur[1];
			for(int k = 0;k < 4;k++){
				int x = x0 + dx[k];
				int y = y0 + dy[k];
				if(x >= 0&&y >= 0&&x < m&&y < n&&board[x][y] == 'W'){
					board[x][y] = 'O';
					q.offer(new int[]{x,y});
				}
			}
		}
		print(board);
	}
	public static void fillDFS(char[][] board,int i,int j){
		dfs(board,i,j);
		print(board);
	}
	public static void dfs(char[][] board,int i,int j){
		for(int k = 0;k < 4;k++){
			int x = i + dx[k];
			int y = j + dy[k];
			if(x >= 0&&y >= 0&&x < board.length&&y < board[0].length&&board[x][y] == 'W'){
				board[x][y] = 'O';
				dfs(board,x,y);
			}
		}
	}
	public static void print(char[][] board){
		for(char[] row : board){
			for(char el : row){
				System.out.print(el);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] map = new String[]{
			"WWWLLLW",
			"WWLLLWW",
			"WLLLLWW"
		};
		int m = map.length;
		int n = map[0].length();
		char[][] board = new char[m][n];
		for(int i = 0;i < m;i++){
			board[i] = map[i].toCharArray();
		}
		print(board);
		System.out.println("Flood");
		fill(board,0,1);
	}

}
