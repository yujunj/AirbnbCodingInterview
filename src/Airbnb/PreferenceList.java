package Airbnb;
import java.util.*;
public class PreferenceList {
	/*********************************
	 * 
	 * preference list
也是地里出现过的了，每个人都有一个preference的排序，在不违反每个人的preference的情况下得到总体的preference的排序
拓扑排序解决
	 * ****************************************/
	public static void preferenceList(int[][] list){
		Map<Integer,Set<Integer>> map = new HashMap<>();
		Map<Integer,Integer> indegrees = new HashMap<>();
		for(int[] l : list){
			for(int i : l){
				if(!map.containsKey(i)){
					indegrees.put(i,0);
				}
			}
		}
		for(int[] one : list){
			for(int i = 0;i < one.length-1;i++){
				int cur = one[i];
				int next = one[i+1];
				Set<Integer> set = new HashSet<>();
				if(map.containsKey(cur)){
					set = map.get(cur);
				}
				if(!set.contains(next)){
					set.add(next);
					indegrees.put(next,indegrees.get(next)+1);	
				}
				map.put(cur, set);
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for(int key : indegrees.keySet()){
			if(indegrees.get(key) == 0){
				q.add(key);
			}
		}
		List<Integer> res = new ArrayList<>();
		while(!q.isEmpty()){
			int top = q.poll();
			res.add(top);
			if(!map.containsKey(top)) continue;
			List<Integer> temp = new ArrayList<>();
			for(int child : map.get(top)){
				int count = indegrees.get(child);
				count--;
				if(count == 0){
					q.add(child);
				}
				indegrees.put(child, count);
			}
		}
		for(int el : res){
			System.out.println(el);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] list = {{3,5,7,9},{2,3,8},{5,8}};
		preferenceList(list);
	}

}
