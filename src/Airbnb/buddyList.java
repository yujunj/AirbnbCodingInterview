package Airbnb;
import java.util.*;
/************************************
 * 
 *  buddy list
followup是给了一个max值，找出你的buddy的wishlist里不在你的wishlist里的最多max个城市，根据buddy和你的重合程度来排序

例如 你的wishlist是 a,b,c,d
buddy1 的wishlist 是 a,b,e,f, 有两个和你的一样，所以是你的buddy
buddy2 的wishlist 是 a,c,d,g, 有三个和你的一样，也是你的budy

问题是输出一个size最多为max的推荐城市列表
当size为10时，buddy1和buddy2的wishlist中不在你的wishlist中的城市都可以加入推荐中，因为buddy2的重合度更高，所以先输出buddy2中的，所以推荐为 g,e,f
当size为2时，推荐是g,e 或 g,f

 * ***************************************/
public class buddyList {
	public static class Buddy{
		int idx;
		int score;
		Set<String> wishSet;
		public Buddy(int idx,int score){
			this.idx = idx;
			this.score = score;
			this.wishSet = new HashSet<>();
		}
	}
	public static void buddy(String cur,List<String> list,int max){
		Buddy[] buddies = new Buddy[list.size()];
		for(int i = 0;i < list.size();i++){
			buddies[i] = new Buddy(i,0);
		}
		Set<String> myList = new HashSet<>();
		String[] myArr = cur.trim().split(",");
		for(String place : myArr){
			myList.add(place);			
		}
		for(int i = 0;i < list.size();i++){
			String other = list.get(i);
			String[] otherArr = other.trim().split(",");
			Set<String> temp = new HashSet<>();
			for(String p : otherArr){
				if(myList.contains(p)){
					buddies[i].score++;
				}
				else{
					temp.add(p);
				}
			}
			if(buddies[i].score > 0){
				buddies[i].wishSet = temp;
			}
		}
		Arrays.sort(buddies,new Comparator<Buddy>(){
			public int compare(Buddy a,Buddy b){
				return b.score - a.score;
			}
		});
		List<String> res = new LinkedList<>();
		for(Buddy b : buddies){
			if(b.score == 0) break;
			Set<String> wish = b.wishSet;
			if(res.size() + wish.size() <= max){
				res.addAll(wish);
			}
			else{
				Iterator<String> itr = wish.iterator();
				for(int i = res.size();res.size() < max;i++){
					res.add(itr.next());
				}
				break;
			}
		}
		for(String s : res){
			System.out.print(s);
		}
		System.out.println();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cur = "a,b,c,d";
		List<String> list = new LinkedList<>();
		list.add("a,b,e,f");
		list.add("a,c,d,g");
		buddy(cur,list,10);
		buddy(cur,list,2);
	}

}
