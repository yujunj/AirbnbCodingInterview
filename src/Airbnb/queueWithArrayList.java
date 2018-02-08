package Airbnb;
import java.util.*;
public class queueWithArrayList {
	List<List<Integer>> queue;
	int maxSize;
	Iterator<List<Integer>> itr;
	Iterator<Integer> cur;
	public queueWithArrayList(int size){
		this.queue = new ArrayList<>();
		this.maxSize = size;
		this.itr = queue.iterator();
	}
	public void enqueue(int num){
		if(queue.size() == 0||queue.get(queue.size()-1).size() == maxSize){
			queue.add(new ArrayList<Integer>());
		}
		queue.get(queue.size()-1).add(num);
	}
	public int dequeue(){
		int res = (int)cur.next();
		cur.remove();
		return res;
	}
	private boolean hasNext(){
		while(cur == null||!cur.hasNext()){
			if(!itr.hasNext()){
				return false;
			}
			cur = (Iterator<Integer>)itr.next();
		}
		return true;
	}
	private Integer next(){
		if(!hasNext()){
			return null;
		}		
		return cur.next();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
