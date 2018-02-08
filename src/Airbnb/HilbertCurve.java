package Airbnb;
import java.util.*;
//Description: 
//每次给你一个(x, y, iter)，问你在iter这张图中在(x, y)坐标的点是第几个？
//解法：从大往小左，逐渐细化。每次先算出当前点在当前iter是在第几象限，先加上前面那些跳过去的象限里的点。
//然后找到这个点在这个象限的相对坐标新(x,y)，但是还不够！对于三四象限的点，因为方向变了，需要做镜面映射，
//把(x,y)映射成(y,x) (第三象限) 或 (M-y, M-x) (第四象限)，M是象限的长宽。
public class HilbertCurve {
	public int getPointOrder(int x,int y,int z){
		if(z == 1){
			if(x == 0&&y == 0){
				return 1;
			}
			else if(x == 0&&y == 1){
				return 2;
			}
			else if(x == 1&&y == 1){
				return 3;
			}
			else{
				return 4;
			}
		}
		else{
			int pointsInOneQ = (int)Math.pow(4,z - 1);
			int sideLength = (int)Math.pow(2,z);
			int mid = sideLength/2;
			if(x < mid&&y < mid){
				return getPointOrder(y,x,z - 1);
			}
			else if(x < mid&&y >= mid){
				return pointsInOneQ + getPointOrder(x,y - mid,z - 1);
			}
			else if(x >= mid&&y >= mid){
				return 2*pointsInOneQ + getPointOrder(x - mid,y - mid,z - 1);
			}
			else{
				return 3*pointsInOneQ + getPointOrder(mid - y,2*mid - x, z - 1);
			}
		}
	}
	public static void main(String[] args){
		 HilbertCurve myS = new HilbertCurve();
		 System.out.println(myS.getPointOrder(6,1,3));
	 	}
	}
