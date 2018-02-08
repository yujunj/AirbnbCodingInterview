package Airbnb;
import java.util.*;
public class WizardPath {
	public class Pair{
		int id;
		int dist;
		public Pair(int id,int dist){
			this.id = id;
			this.dist = dist;
		}
	}
	public class Edge{
		public int from;
		public int to;
		public int weight;
		public Edge(int from,int to,int weight){
			this.from = from;
			this.to= to;
			this.weight = weight;
		}
	}
	public void shortestPath(int[][] relations,int dest){
		int[] dist = new int[10];
		int[] pred = new int[10];
		Set<Edge>[] graph = new HashSet[10];
		boolean[] visited = new boolean[10];
		for(int i = 0;i < 10;i++){
			dist[i] = Integer.MAX_VALUE;
			graph[i] = new HashSet<Edge>();
		}
		for(int[] relation : relations){
			int from = relation[0];
			int to = relation[1];
			int weight = (to-from)*(to-from);
			graph[from].add(new Edge(from,to,weight));
		}
		dist[0] = 0;
		//visited[0] = true;
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
			public int compare(Pair a,Pair b){
				return a.dist-b.dist;
			}
		});
		for(Edge e : graph[0]){
			dist[e.to] = 0;
			pq.offer(new Pair(e.to,0));
			pred[e.to] = 0;
		}
		pq.offer(new Pair(0,0));
		while(!pq.isEmpty()){
			Pair cur = pq.poll();
			int id = cur.id;
			if(visited[id]) continue;
			visited[id] = true;
			for(Edge next : graph[id]){	
				if(!visited[next.to]&&dist[next.to] > dist[cur.id] + next.weight){					
					dist[next.to] = dist[cur.id] + next.weight;
					pred[next.to] = id;
					pq.offer(new Pair(next.to,dist[next.to]));
				}
			}
		}
		int i = dest;
		System.out.println(i);
		while(pred[i] != 0){
			System.out.println(pred[i]);
			i = pred[i];
		}
		System.out.println(0);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] relations = new int[15][2];
		relations[0][0] = 0;relations[0][1] = 1;
		relations[1][0] = 2;relations[1][1] = 3;
		relations[2][0] = 1;relations[2][1] = 4;
		relations[3][0] = 3;relations[3][1] = 2;
		relations[4][0] = 5;relations[4][1] = 6;
		relations[5][0] = 5;relations[5][1] = 3;
		relations[6][0] = 7;relations[6][1] = 9;
		relations[7][0] = 8;relations[7][1] = 9;
		relations[8][0] = 4;relations[8][1] = 6;
		relations[9][0] = 1;relations[9][1] = 3;
		relations[10][0] = 6;relations[10][1] = 3;
		relations[11][0] = 6;relations[11][1] = 8;
		relations[12][0] = 4;relations[12][1] = 2;
		relations[13][0] = 5;relations[13][1] = 8;
		relations[14][0] = 2;relations[14][1] = 6;
		new WizardPath().shortestPath(relations,9);
	}

}
