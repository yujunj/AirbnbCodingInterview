package Airbnb;

import java.util.*;
/****************************
 * √ Meeting Room 找T1-T2内所有人空的时间段

Q: 给一组meetings（每个meeting由start和end时间组成）。求出在所有输入meeting时间段内没有会议，也就是空闲的时间段。每个subarray都已经sort好。N个员工，每个员工有若干个interval表示在这段时间是忙碌的。求所有员工都不忙的intervals。
举例：
[
  [[1, 3], [6, 7]],
  [[2, 4]],
  [[2, 3], [9, 12]]
]
[[4, 6], [7, 9]]

A: 这题最简单的方法就是把所有区间都拆成两个点，然后排序，然后扫描，每次碰到一个点如果是左端点就把busy_employees加1，否则减1，等到每次busy_employees为0时就是一个新的区间。这样复杂度O(MlogM)，M是总共区间数。

 * **********************************/
public class NoMeetingEmployee {
	public static class Node{
		int val;
		int isStart;
		public Node(int val,int isStart){
			this.val = val;
			this.isStart = isStart;
		}
	}
	public static void noMeetingCount(int[][] meetings){
		List<Node> list = new LinkedList<>();
		for(int[] meeting : meetings){
			list.add(new Node(meeting[0],1));
			list.add(new Node(meeting[1],-1));
		}
		Collections.sort(list,new Comparator<Node>(){
			public int compare(Node a,Node b){
				return a.val != b.val ? a.val - b.val:a.isStart - b.isStart;
			}
		});
		int count = 0;
		List<Node> res = new ArrayList<>();
		for(int i = 0;i < list.size();i++){
			Node node = list.get(i);
			if(node.isStart == 1){
				if(count == 0&&res.size() > 0){
					res.add(new Node(node.val,-1));
				}
				count++;
			}
			else{
				count--;
				if(count == 0&&i != list.size()-1){
					res.add(new Node(node.val,1));
				}
			}			
		}
		for(Node node : res){
			if(node.isStart == 1){
				System.out.println("start :"+node.val);
			}
			else{
				System.out.println("end :"+node.val);
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] meetings = {{1,3},{6,7},{2,4},{2,3},{9,12}};
		noMeetingCount(meetings);
	}

}