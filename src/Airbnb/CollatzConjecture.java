package Airbnb;
import java.util.*;
/***************************************/
/**Collatz Conjecture Definition:
/**If the number is even, divide it by two.
/**If the number is odd, triple it and add one.
/**************************************/
public class CollatzConjecture {
	public static void CalSteps(int n){
		Map<Long,Integer> map = new HashMap<>();
		int res = -1;
		int max = Integer.MIN_VALUE;
		for(int i = 1;i <= n;i++){
			int count = calSteps((long)i,map);
			if(count == -1) continue;
			if(count > max){
				max = count;
				res = i;
			}
		}
		System.out.println(max);
		System.out.println(res);
		System.out.println("\n");
	}
	public static int calSteps(long n,Map<Long,Integer> map){
		int count = 0;
		while(n != 1){
			if(n%2 == 1){
				n = 3*n + 1;
			}
			else{
				n = n/2;
			}
			count++;
			if(!map.containsKey(n)){
				map.put(n,count);
			}
			else{
				//it means we have a previous larger count to reach 1 at this step
				if(map.get(n) > count){
					return -1;
				}
			}
		}
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalSteps(10);//19
		CalSteps(100);//118
		CalSteps(1000);//178
		CalSteps(10000);//261
		CalSteps(100000);//350
		CalSteps(1000000);//524
		
	}

}
