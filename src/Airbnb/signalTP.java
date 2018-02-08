package Airbnb;
import java.util.*;
public class signalTP {
	/***************
	 * "s1,s2 : s3" means 1 and 2 depends on 2
	 * "s4:" means s4 depends on nothing
	 * "s5: s1,s6" means 5 depends on 1 and 6
	 * output an arraylist of string of sequence of signals
	 * * ***************/
	public void sort(ArrayList<String> list){
		HashMap<String,Set<String>> map = new HashMap<>();
		HashMap<String,Integer> degrees = new HashMap<>();
		//Get all the incoming signals
		for(String str : list){
			int idx = str.indexOf(":");
			String[] children = str.substring(0,idx).split(",");
			String[] parents = str.substring(idx+1).equals("")?null:str.substring(idx+1).split(",");
			for(String child : children){
				child = child.trim();
				if(!degrees.containsKey(child)){
					degrees.put(child,0);
				}
			}
			if(parents == null) continue;
			for(String parent : parents){
				parent = parent.trim();
				if(!degrees.containsKey(parent)){
					degrees.put(parent,0);
				}
			}
		}
		for(String str : list){
			int idx = str.indexOf(':');
			String[] children = str.substring(0,idx).split(",");
			String[] parents = str.substring(idx+1).equals("")?null:str.substring(idx+1).split(",");
			for(String child : children){
				child = child.trim();
				if(parents == null||parents.length == 0) continue;
				for(String parent : parents){
					//set should be put inside the loop because every parent should have a separate set.
					Set<String> set = new HashSet<>();
					//System.out.println(parent);
					parent = parent.trim();
					if(map.containsKey(parent)){
						set = map.get(parent);
					}
					if(!set.contains(child)){
						set.add(child);
						degrees.put(child,degrees.get(child)+1);
					}
					map.put(parent,set);
				}
			}
		}
//		System.out.println(degrees.get("signal1"));
//		System.out.println(degrees.get("signal2"));
//		System.out.println(degrees.get("signal3"));
		//System.out.println(map.size());
		Queue<String> q = new LinkedList<>();
		for(String key : degrees.keySet()){
			if(degrees.get(key) == 0){
				q.offer(key);
				//System.out.println(key);
			}
		}
		List<String> res = new ArrayList<>();
		while(!q.isEmpty()){
			String cur = q.poll();
			res.add(cur);
			if(!map.containsKey(cur)||map.get(cur).size() == 0) continue;
			for(String key : map.get(cur)){
				int count = degrees.get(key);
				count--;
				if(count == 0){
					q.offer(key);
				}
				degrees.put(key,count);
			}
		}
		if(degrees.size() != res.size()){
			System.out.println("Loop in the sequence");
		}
		else{
			for(String str : res){
				System.out.println(str);
			}
		}
	}
	

    
	public static void main(String[] args){
		ArrayList<String> list = new ArrayList<String>();
//		list.add("signal1:signal2,signal3");
//		list.add("signal4:");
//		list.add("signal5:signal1,signal6");
//		list.add("signal6:signal4,signal7");
//		list.add("signal2:signal4,signal7");
		list.add("signal1:signal2,signal3");
		list.add("signal2:signal1");
		new signalTP().sort(list);
	}
}