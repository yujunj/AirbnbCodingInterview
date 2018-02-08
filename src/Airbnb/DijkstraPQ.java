package Airbnb;
import java.util.*;

import Airbnb.minCostKConnection.Edge;
 
public class DijkstraPQ
{
	public class Edge{
		public String from;
		public String to;
		public int weight;
		public Edge(String from,String to,int weight){
			this.from = from;
			this.to= to;
			this.weight = weight;
		}
	}
	public class Pair{
		String id;
		int dist;
		public Pair(String id,int dist){
			this.id = id;
			this.dist = dist;
		}
	}
	Map<String,Set<Edge>> graph = new HashMap<>();
	public void shortestPath(List<String> list,String source){
		Map<String,Integer> dist = new HashMap<>();
		for(String elm : list){
			String[] els = elm.trim().split(",");
			graph.put(els[0],new HashSet<Edge>());
			graph.put(els[1],new HashSet<Edge>());
			dist.put(els[0],Integer.MAX_VALUE);
			dist.put(els[1],Integer.MAX_VALUE);
		}
		for(String trip : list){
			String[] els = trip.trim().split(",");
			graph.get(els[0]).add(new Edge(els[0],els[1],Integer.parseInt(els[2])));
		}
		Set<String> visited = new HashSet<>();
		dist.put(source, 0);
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
			public int compare(Pair a,Pair b){
				return a.dist - b.dist;
			}
		});
		pq.offer(new Pair(source,0));
		while(!pq.isEmpty()){
			Pair cur = pq.poll();
			String key = cur.id;
			if(visited.contains(key)) continue;
			visited.add(key);
			for(Edge e : graph.get(key)){
				String next = e.to;
				if(dist.get(next) > dist.get(key) + e.weight){
					dist.put(next,dist.get(key) + e.weight);
					pq.add(new Pair(next,dist.get(next)));
				}
			}
		}
		for(Map.Entry<String,Integer> entry : dist.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
    
    public static void main(String[] args)
    {
    	List<String> list = new LinkedList<>();
		list.add("A,B,100");
		list.add("A,C,500");
		list.add("B,A,50");
		list.add("B,C,1000");
		new DijkstraPQ().shortestPath(list,"B");
    }	
}
