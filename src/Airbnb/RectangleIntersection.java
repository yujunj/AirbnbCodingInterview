package Airbnb;
import java.util.*;
public class RectangleIntersection {
	int count;
	public int getCount(){
		return count;
	}
	public class UF{
		int[] id;
		int[] size;
		public UF(int n){
			this.id = new int[n];
			this.size = new int[n];
			for(int i = 0;i < n;i++){
				id[i] = i;
			}
		}
		public int find(int p){
			while(p != id[p]){
				id[p] = id[id[p]];
				p = id[p];
			}
			//System.out.println(p);
			return p;
		}
		public void union(int p,int q){
			int rootP = find(p);
			int rootQ = find(q);
			if(rootP == rootQ) return;
			if(size[rootP] < size[rootQ]){
				id[rootP] = rootQ;
				size[rootQ] += size[rootP];
			}
			else{
				id[rootQ] = rootP;
				size[rootP] += size[rootQ];
			}
			count--;
		}
	}
	public boolean isIntersected(int[][] rec1,int[][] rec2){
		int left = Math.max(rec1[0][0],rec2[0][0]);
		int right = Math.min(rec1[1][0], rec2[1][0]);
		int bottom = Math.max(rec1[0][1], rec2[0][1]);
		int top = Math.min(rec1[1][1],rec2[1][1]);
		return (right > left)&&(top > bottom);
	}
	public void countIntersection(List<int[][]> list){
		this.count = list.size();
		UF uf = new UF(this.count);
		for(int i = 0;i < list.size();i++){
			for(int j = i+1;j < list.size();j++){
				if(isIntersected(list.get(i),list.get(j))){
					uf.union(i, j);
				}
			}
		}
		System.out.println(count);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] rec1 = {{0,0},{2,2}};
		int[][] rec2 = {{1,1},{3,3}};
		int[][] rec3 = {{-3,0},{-1,1}};
		int[][] rec4 = {{-2,0},{1,1}};
		int[][] rec5 = {{0,-2},{2,-1}};
		int[][] rec6 = {{1,-2},{2,2}};
		List<int[][]> list = new LinkedList<>();
		list.add(rec1);
		list.add(rec2);
		list.add(rec3);
		list.add(rec4);
		list.add(rec5);
		list.add(rec6);
		new RectangleIntersection().countIntersection(list);
	}

}
