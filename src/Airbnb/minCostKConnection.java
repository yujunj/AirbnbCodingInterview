package Airbnb;
import java.util.*;
/************************************
 * 
 * Min cost path allowing k connections

A: 本质就是BFS一层一层往外扫。
min cost of flight from start to end if allowed at most k connections. 比如：
A->B, 100,
B->C, 100,
A->C, 500.
如果k是1的话，起点终点是A，C的话，那A->B->C最好, 我只想到BFS的解法。

 * ***********************************/
public class minCostKConnection {
	public class Edge{
		String to;
		String from;
		int weight;
		public Edge(String to,String from,int weight){
			this.to = to;
			this.from = from;
			this.weight = weight;
		}		
	}
	public int getMinCost(List<String> list,int k,String origin,String dest){
		Map<String,Set<Edge>> graph = new HashMap<>();
		int min = Integer.MAX_VALUE;
		if(origin.equals(dest)){
			return 0;
		}
		for(String trip : list){
			String[] els = trip.trim().split(",");
			graph.put(els[0],new HashSet<>());
			graph.put(els[1],new HashSet<>());
		}
		for(String trip : list){
			String[] els = trip.trim().split(",");
			graph.get(els[0]).add(new Edge(els[1],els[0],Integer.parseInt(els[2])));
		}
		Queue<String> q = new LinkedList<>();
		Queue<Integer> cost = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		//visited.add(origin);
		q.add(origin);
		cost.add(0);
		int i = 1;
		while(i < k&&!q.isEmpty()){
			for(int j = q.size()-1;j >= 0;j--){
				String cur = q.poll();
				//if(visited.contains(cur)) continue;
				//visited.add(cur);
				System.out.println(cur);
				int curCost = cost.poll();
				if(cur.equals(dest)){
					min = Math.min(min,curCost);
				}
				if(graph.get(cur).size() == 0) continue;
				for(Edge next : graph.get(cur)){
					int temp = curCost;
					temp += next.weight;
					q.add(next.to);
					cost.add(temp);
				}
				k++;
			}			
		}
		return min;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new LinkedList<>();
		list.add("A,B,100");
		list.add("B,C,200");
		list.add("A,C,500");
		minCostKConnection test = new minCostKConnection();
		System.out.println(test.getMinCost(list,3,"A","C"));
	}

}
