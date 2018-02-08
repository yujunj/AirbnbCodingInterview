package Airbnb;
import java.util.*;
import Airbnb.Board;	
public class SlidingPuzzles {
		int totalMoves;
		SearchNode last;
		Board initial;
		public class SearchNode{
			private Board board;
			private int moves;
			private SearchNode prev;
			private int weight;
			public SearchNode(Board b,int m,SearchNode p){
				board = b;
				moves = m;
				prev = p;
				weight = b.manhattan() + moves;
			}
		}
		//helper for adding next search nodes to the queue
		public void enqueueNodes(SearchNode node,PriorityQueue<SearchNode> pq){
			for(Board nextBoard : node.board.neighbors()){
				if(node.prev == null||!nextBoard.toString().equals(node.prev.board.toString())){
					pq.offer(new SearchNode(nextBoard,node.moves+1,node));
				}
			}
		}
	public SlidingPuzzles(Board initial){
		this.initial = initial;
	}
	public void solve(){
		PriorityQueue<SearchNode> pq = new PriorityQueue<SearchNode>(new Comparator<SearchNode>(){
			public int compare(SearchNode s1,SearchNode s2){
				return s1.weight - s2.weight;
			}
		});
		SearchNode start = new SearchNode(initial,0,null);
		pq.offer(start);
		while(!pq.isEmpty()){
			SearchNode cur = pq.poll();
			if(cur.board.isGoal()){
				last = cur;
				totalMoves = cur.moves;
				break;
			}
			enqueueNodes(cur,pq);
		}
		System.out.println("moves :"+totalMoves);
		while(last.prev != null){
			System.out.println(last.board);
			last = last.prev;
		}
	}
	public static void main(String[] args) {
        //int[][] tiles = {{1, 3, 7}, {0, 2, 8}, {4, 6, 5}};
        //int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] blocks = {{8, 1, 3}, {4, 2, 0}, {7, 6, 5}};
        //int[][] tiles = {{1, 0}, {2, 3}};
        Board a = new Board(blocks);
        new SlidingPuzzles(a).solve();
    }
}
