package Airbnb;
import java.util.*;
public class orderSum {
	private final double e = 0.01;
	public void order(ArrayList<Double> nums,double target){
		Collections.sort(nums);
		//dfs(nums,0.0,target,0,new ArrayList<Double>());
		System.out.println("No Dups dish");
		dfsNoDuplicate(nums,0.0,target,0,new ArrayList<Double>());
	}
	public void dfs(ArrayList<Double> nums,double sum,double target,int start,ArrayList<Double> oneres){
		if(Math.abs(sum-target) < e){
			for(Double dish : oneres){
				System.out.println(dish);
			}
			System.out.println("\n");
			return;
		}
		if(start >= nums.size()||sum-target >= e){
			return;
		}
		for(int i = start;i < nums.size();i++){
			if(sum+nums.get(i) - target >= e) break;
			oneres.add(nums.get(i));
			dfs(nums,sum+nums.get(i),target,i,oneres);
			oneres.remove(oneres.size()-1);
		}
	}
	public void dfsNoDuplicate(ArrayList<Double> nums,double sum,double target,int start,ArrayList<Double> oneres){
		if(Math.abs(sum-target) < e){
			for(Double dish : oneres){
				System.out.println(dish);
			}
			System.out.println("\n");
			return;
		}
		if(start >= nums.size()||sum-target >= e){
			return;
		}
		for(int i = start;i < nums.size();i++){
			if(sum+nums.get(i) - target >= e) break;
			oneres.add(nums.get(i));
			dfsNoDuplicate(nums,sum+nums.get(i),target,i+1,oneres);
			oneres.remove(oneres.size()-1);
			while(i < nums.size()-1&&nums.get(i) == nums.get(i+1)) i++;
		}
	}
	public static void main(String[] args) {
// TODO Auto-generated method stub
		ArrayList<Double> list = new ArrayList<>();
		list.add(2.75);
		list.add(3.25);
		list.add(4.0);
		list.add(3.5);
		list.add(6.5);
		list.add(2.45);
		list.add(3.65);
		list.add(7.35);
		list.add(4.85);
		list.add(9.05);
		new orderSum().order(list,13.25);
	}
}