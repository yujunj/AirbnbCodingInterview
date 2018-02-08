package Airbnb;
import java.util.*;
/***********************************
 * skype: 往一个int array 代表海拔的格子里倒水，打印出倒水后的图， 输入：int[] 海拔， int 水数量， int 倒得位置。
Example:
int[] 海拔 {5,4,2,1,2,3,2,1,0,1,2,4}
+           
++      +
++  +   ++
+++ +++  ++
++++++++ +++
++++++++++++
012345
水数量8， 倒在位置5 ->
+           
++      +
++www+   ++
+++w+++www++
++++++++w+++
++++++++++++
 * *******************************************/
public class WaterFlood {
	//Assume that water flood to the left first:
	//---when flooding to the left,flood leftmost location first;
	//---when flooding to the right,flood rightmost location first.
	public static void flood(int[] heights,int amount,int pos){
		int[] temp = new int[heights.length];
		for(int i = 0;i < heights.length;i++){
			temp[i] = heights[i];
		}
		int amt = 0;
		while(amt < amount&&floodLeft(heights,pos)) amt++;
		if(amt == amount){
			printMap(heights,temp);
		}
		while(amt < amount&&floodRight(heights,pos)) amt++;
		//check for flat area
		while(amt < amount){
			int i = pos;
			int j = pos;
			int pivot = heights[pos];
			while(i >= 0&&heights[i] == pivot) i--;
			while(j < heights.length&&heights[j] == pivot) j++;
			i++;
			j--;
			for(int k = i;k <= j&&amt < amount;k++){
				heights[k]++;
				amt++;
			}
		}
		printMap(heights,temp);
	}
	private static void printMap(int[] heights,int[] temp){
		int max = 0;
		for(int height : temp){
			max = Math.max(height,max);
		}
		for(int j = max;j >= 0;j--){
			for(int i = 0;i < temp.length;i++){
				if(temp[i] >= j){
					System.out.print('+');
				}
				else if(heights[i] >= j){
					System.out.print('w');
				}
				else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}

		System.out.println();
	}
	private static boolean floodLeft(int[] heights,int pos){
		int pivot = heights[pos];
		pos--;
		while(pos >= 0&&heights[pos] <= heights[pos+1]) pos--;
		pos++;
		if(heights[pos] >= pivot){
			return false;
		}
		heights[pos]++;
		return true;
	}
	private static boolean floodRight(int[] heights,int pos){
		int pivot = heights[pos];
		pos++;
		while(pos < heights.length&&heights[pos] <= heights[pos-1]) pos++;
		pos--;
		if(heights[pos] >= pivot){
			return false;
		}
		heights[pos]++;
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] heights = {5,4,2,1,2,3,2,1,0,1,2,4};
		int[] heights2 = {3,3,3,2,0,0,0};
		int[] heights3 = {3,3,3,3,3};
		int[] heights4 = {3,1,4,2,5,3,7,6,3,1,6};
		//flood(heights,8,5);
		//flood(heights2,10,5);
		//flood(heights3,4,3);
		flood(heights4,27,6);
	}

}
