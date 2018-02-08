package Airbnb;
import java.util.*;
public class Board {
	int[][] tiles;
	int N;//tile size
	public Board(int[][] blocks){
		N = blocks.length;
		tiles = copyBoard(blocks);
	}
	private int[][] copyBoard(int[][] board){
		int[][] b = new int[N][N];
		for(int i = 0;i < N;i++){
			for(int j = 0;j < N;j++){
				b[i][j] = board[i][j];
			}
		}
		return b;
	}
	public int hamming(){
		int numOfDisplacedBlocks = 0;
		for(int i = 0;i < N;i++){
			for(int j = 0;j < N;j++){
				if(tiles[i][j] != j + i*N + 1){
					numOfDisplacedBlocks++;
				}
			}
		}
		return numOfDisplacedBlocks - 1;//exclude blank block 
	}
	private int singleMan(int i,int j){
		int x = tiles[i][j]/N;
		int y = tiles[i][j]%N;
		if(y == 0){
			x--;
			y = N - 1;
		}
		else{
			y--;
		}
		return Math.abs(x-i) + Math.abs(y-j);
	}
	public int manhattan(){
		int manDist = 0;
		for(int i = 0;i < N;i++){
			for(int j = 0;j < N;j++){
				if(tiles[i][j] != 0&&tiles[i][j] != i*N + j + 1){
					manDist += singleMan(i,j);
				}
			}
		}
		return manDist;
	}
	public boolean isGoal(){
		return manhattan() == 0;
	}
	private void swapBlocks(int[][] b,int i,int j,int x,int y){
		int temp = b[i][j];
		b[i][j] = b[x][y];
		b[x][y] = temp;
	}
	//push neighbor onto the stack
	private void pushBoardOntoStack(Stack<Board> s,int i,int j,int x,int y){
		int[][] neighbor = copyBoard(tiles);
		swapBlocks(neighbor,i,j,x,y);
		s.push(new Board(neighbor));
	}
	public List<Board> neighbors(){
		Stack<Board> s = new Stack<>();
		boolean blankFound = false;
		for(int i = 0;i < N&&!blankFound;i++){
			for(int j = 0;j < N;j++){
				if(tiles[i][j] == 0){
					blankFound = true;
					if(i > 0) pushBoardOntoStack(s,i,j,i - 1,j);
					if(i < N - 1) pushBoardOntoStack(s,i,j,i + 1,j);
					if(j > 0) pushBoardOntoStack(s,i,j,i,j - 1);
					if(j < N - 1) pushBoardOntoStack(s,i,j,i,j + 1);
					break;
				}
			}
		}
		return s;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(N + "\n");
		for(int i = 0;i < N;i++){
			for(int j = 0;j < N;j++){
				sb.append(tiles[i][j]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
        //int[][] tiles = {{1, 3, 7}, {0, 2, 8}, {4, 6, 5}};
        //int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] blocks = {{8, 1, 3}, {4, 2, 0}, {7, 6, 5}};
        //int[][] tiles = {{1, 0}, {2, 3}};
        Board a = new Board(blocks);
        System.out.println(a.manhattan());
        System.out.println(a);
        for (Board board: a.neighbors()) {
        	System.out.println(board);
        }
    }
}